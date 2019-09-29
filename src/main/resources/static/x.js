// 提示：请选择一条记录
function tipSelOne() {
    $.messager.show({
        msg : g_tip_selOnlyOne
    });
}

// 提示：请选择记录
function tipSelRecord() {
    $.messager.show({
        msg : g_tip_selRecord
    });
}

// 提示：成功
function tipSuccess() {
    $.messager.show({
        msg : g_tip_success
    });
}

// 提示：失败
function tipFail(message) {
    $.messager.show({
        msg : message
    });
}

// 返回按钮
function rebackBnt(dlg) {
    return {
        text : bnt_reback,
        iconCls : 'icon-back',
        handler : function() {
            dlg.dialog('close');
        }
    }
}

// ///////////////////////////////

// 表格中“是、否”渲染
function yesOrNoRender(v) {
    if (v == 1) {
        return '<font color="green">' + g_yes + '</font>';
    } else if (v == 0) {
        return '<font color="red">' + g_no + '</font>';
    } else {
        return '';
    }
}

// 字典表checkbox
function appendDictCheckbox(parentDom, name, dictCode, excludeArr) {
    $.post("console/sys/dict/selDict/" + dictCode, function(rs) {
        var k = rs.length;
        for (var i = 0; i < k; i++) {
            if (excludeArr && $.inArray(rs[i].value, excludeArr) > -1) {
                continue;
            }
            var s = '&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="' + name + '" value="' + rs[i].value + '">' + rs[i].text;
            $(parentDom).append(s);
        }
    }, "json");
}

// ///////////////////////////////

// 创建弹出框Iframe
function createIframe(url) {
    return '<iframe src="' + url + '" scrolling="no" frameborder="0" style="width: 100%;height: 98%;"></iframe>';
}

// excel模板下载
function xlsDownload(url) {
    window.open(url);
}

function createDownLoadA(P, fileName) {
    if (!!fileName) {
        P.html('<a href="javascript:" onclick="xlsDownload(\'' + encodeURI('file/fileDownload/' + fileName) + '\')">' + fileName + '</a>');
    } else {
        P.empty();
    }
}

// td-img-dblclick
function tdImgDdlclick(frmId, notModal) {
    var frm = $("#" + frmId);
    frm.find(".tabImg tr:not(:first) td").unbind("dblclick").bind("dblclick", function() {
        if ($(this).html() != "") {
            var src = $(this).find("img").attr("src");
            $("#imgDialog").dialog({
                title : pictureViewer,
                modal : !notModal,
                content : '<img width="700px" height="450px" src="' + src + '" />'
            }).dialog('center').dialog('open');
        }
    });
}

// 展示所有图片
function editAfterImg(frmId) {
    var frm = $("#" + frmId);
    frm.find(".tabImg input[type='hidden']").each(function(i) {
        var url = $(this).val();
        var _td = frm.find("#" + $(this).attr("name"));
        if (url) {
            _td.html('<img width="98%" height="95px" src="panel/custcpt/showImg?filePath=' + url + '" />');
        } else {
            _td.empty();
        }
    });
}

// 显示解决方案
function showSolution() {
    var dlg = $("#tab-solution");
    var _solutionType = dlg.find("input[name=solutionType]").val();
    if (_solutionType == 'Replacement') {
        dlg.find(".Replacement").show();
        dlg.find(".Reimbursement").hide();
    } else if (_solutionType == 'Reimbursement') {
        dlg.find(".Replacement").hide();
        dlg.find(".Reimbursement").show();
    } else {
        dlg.find(".Replacement").hide();
        dlg.find(".Reimbursement").hide();
    }
}

// ///////////////////////////////

// 序列化form为json对象
$.fn.serializeForm = function() {
    var rs = {};
    var frm = this.serializeArray();
    $.each(frm, function() {
        if (rs[this.name]) {
            if ($.isArray(rs[this.name])) {
                rs[this.name].push(this.value);
            } else {
                rs[this.name] = [ rs[this.name], this.value ];
            }
        } else {
            rs[this.name] = this.value;
        }
    });
    return rs;
};

// view-input赋值
function setViewValue(P, rs) {
    for (k in rs) {
        P.find("input[name=" + k + "]").val(rs[k]);
        P.find("input[textboxname=" + k + "]").textbox('setValue', rs[k]);
    }
}

// 灰显
function inputDisabled(disabledArr, P) {
    if (disabledArr) {
        $.each(disabledArr, function(i, v) {
            P.find("input[textboxname='" + v + "']").textbox('textbox').attr('disabled', true);
        });
    }
}

// 显示
function inputEnabled(enabledArr, P) {
    if (enabledArr) {
        $.each(enabledArr, function(i, v) {
            P.find("input[textboxname='" + v + "']").textbox('textbox').attr('disabled', false);
        });
    }
}

$.fn.extend({

    /**
     * 编辑form
     * 
     * <pre>
     * {
     *     title : '非屏体投诉单-新增',
     *     viewType : 'add',
     *     dg : 'dg',
     *     maximized : false,
     *     istreegrid : false,
     *     disabledArr : [ 'createUserName', 'createTime', 'modUserName', 'modTime' ],
     *     enabledArr : [ 'createUserName', 'createTime', 'modUserName', 'modTime' ],
     *     submitBefore : submitBeforeFunc,
     *     bnts : [ {
     *         text : bnt_save,
     *         iconCls : 'icon-ok',
     *         url : 'console/sys/dict/add',
     *         display : true
     *     } ]
     * }
     * </pre>
     */
    editFormDialog : function(opts) {
        var me = this;
        var frm = $(this).find("form");

        // enable-disable
        frm.form("enable");
        inputDisabled(opts.disabledArr, frm);

        // 按钮
        var bnts = [];
        if (opts.bnts) {
            $.each(opts.bnts, function(index, obj) {
                if (false !== obj.display) {
                    bnts.push({
                        text : obj.text || bnt_save, // 默认值
                        iconCls : obj.iconCls || 'icon-save', // 默认值
                        handler : function() {
                            $.messager.progress();
                            frm.form('submit', {
                                url : obj.url,
                                onSubmit : function() {
                                    if (!$(this).form('validate') || (opts.submitBefore && !opts.submitBefore())) {
                                        $.messager.progress('close');
                                        return false;
                                    }
                                    return true;
                                },
                                success : function(rs) {
                                    var json = $.parseJSON(rs);
                                    if (json.code) {
                                        tipSuccess();
                                        $(me).dialog('close');
                                        if (!!opts.istreegrid) {
                                            $('#' + opts.dg).treegrid('reload');
                                        } else {
                                            $('#' + opts.dg).datagrid('reload');
                                        }
                                    } else {
                                        tipFail(json.data);
                                    }
                                    $.messager.progress('close');
                                }
                            });
                        }
                    });
                }
            });
        }
        bnts.push(rebackBnt($(me)));

        $(me).dialog({
            title : opts.title,
            maximized : !!opts.maximized,
            toolbar : bnts,
            onClose : function() {
                frm.form('reset');
            }
        }).dialog('center').dialog('open');
    },

    // 查询form：{dg : 'dg'}
    qryFormDialog : function(opts) {
        var me = this;
        var frm = $(this).find("form");

        $(me).dialog({
            title : qry_condition,
            buttons : [ {
                text : bnt_qry,
                iconCls : 'icon-search',
                handler : function() {
                    $('#' + opts.dg).datagrid({
                        queryParams : frm.serializeForm()
                    });
                    $(me).dialog('close');
                }
            }, {
                text : bnt_reset,
                iconCls : 'icon-redo',
                handler : function() {
                    frm.form('reset');
                }
            } ]
        }).dialog('center').dialog('open');
    },

    // 选择订单
    orderDialog : function(opts) {
        var me = this;
        var frm = $(me).find("form");
        var tab = $("#dg-order");
        tab.datagrid({
            url : 'order/qryForPage',
            queryParams : frm.serializeForm(),
            height : 340,
            onSelect : function() {
                $("#selBnt").linkbutton('enable');
            },
            onLoadSuccess : function() {
                $("#selBnt").linkbutton('disable');
            }
        });

        $(me).dialog({
            title : order_select,
            toolbar : [ {
                id : "selBnt",
                text : bnt_select,
                iconCls : 'icon-ok',
                plain : false,
                handler : function() {
                    opts.selRow(tab.datagrid("getSelected"));
                    $(me).dialog('close');
                }
            }, {
                text : bnt_qry,
                iconCls : 'icon-search',
                plain : false,
                handler : function() {
                    tab.datagrid({
                        queryParams : frm.serializeForm()
                    });
                }
            }, {
                text : bnt_reset,
                iconCls : 'icon-redo',
                plain : false,
                handler : function() {
                    frm.form('reset');
                }
            } ]
        }).dialog('center').dialog('open');
    },

    // 导入
    excelUpload : function(opts) {
        var me = this;

        var _frmStr = "";
        _frmStr += '<form enctype="multipart/form-data" method="post" action="' + opts.url + '">';
        _frmStr += '  <div style="padding: 15px; width: 450px;">';
        _frmStr += '      <input class="easyui-filebox" id="fileName" name="fileName" style="width: 80%;" data-options="prompt:\'' + g_xls_tip + '\',required:true,accept:\'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\',buttonText:\'' + browse + '\'">';
        _frmStr += '      <a class="easyui-linkbutton">' + bnt_import + '</a>';
        _frmStr += '  </div>                                    ';
        _frmStr += '</form>                                     ';

        var frm = $(_frmStr);
        frm.find('.easyui-linkbutton').bind("click", function() {
            frm.form('submit', {
                onSubmit : function() {
                    $.messager.progress();
                    if (!$(this).form('validate')) {
                        $.messager.progress('close');
                        return false;
                    }
                },
                success : function(rs) {
                    var json = $.parseJSON(rs);
                    if (json.code) {
                        tipSuccess();
                        $(me).dialog('close');
                        $('#' + opts.dg).datagrid("reload");
                    } else {
                        $.messager.alert(g_tip_title, json.data);
                    }
                    $.messager.progress('close');
                }
            });
        });

        $(me).empty().append(frm);
        $.parser.parse(me);// 重新渲染

        $(me).dialog({
            title : bnt_import
        }).dialog('center').dialog('open');
    },

    // 文件
    fileUpload : function(opts) {
        var me = this;

        var _frmStr = "";
        _frmStr += '<form enctype="multipart/form-data" method="post" action="panel/cscpt/fileUpload">';
        _frmStr += '  <div style="padding: 15px; width: 450px;">';
        _frmStr += '      <input class="easyui-filebox" id="fileName" name="fileName" style="width: 80%;" data-options="required:true,buttonText:\'' + browse + '\'">';
        _frmStr += '      <a class="easyui-linkbutton">' + bnt_import + '</a>';
        _frmStr += '  </div>                                    ';
        _frmStr += '</form>                                     ';

        var frm = $(_frmStr);
        frm.find('.easyui-linkbutton').bind("click", function() {
            frm.form('submit', {
                onSubmit : function() {
                    $.messager.progress();
                    if (!$(this).form('validate')) {
                        $.messager.progress('close');
                        return false;
                    }
                },
                success : function(rs) {
                    var json = $.parseJSON(rs);
                    if (json.code) {
                        tipSuccess();
                        $(me).dialog('close');
                        opts.fileBox.textbox("setValue", json.data);
                        createDownLoadA(opts.displayDoc, json.data);
                    } else {
                        $.messager.alert(g_tip_title, json.data);
                    }
                    $.messager.progress('close');
                }
            });
        });

        $(me).empty().append(frm);
        $.parser.parse(me);// 重新渲染

        $(me).dialog({
            title : bnt_fileUpload
        }).dialog('center').dialog('open');
    }
});