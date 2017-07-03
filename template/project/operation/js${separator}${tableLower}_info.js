/**
 * 初始化@{crud.table.remarks}详情对话框
 */
var @{crud.table.className}InfoDlg = {
	@{crud.table.className}InfoData : {},
	validateFields: {
		# for(column in crud.table.notNullColumns){ #
        	# if( columnLP.last ) { #
        @{strutils.toLowerCaseFirst(column.columnJavaName)}: {
              validators: {
                notEmpty: {
                  message: '@{column.remarks}不能为空'
                }
              }
        }
			# } else { #
		@{strutils.toLowerCaseFirst(column.columnJavaName)}: {
		      validators: {
		        notEmpty: {
		          message: '@{column.remarks}不能为空'
		        }
		      }
		},			
			# } #
        # } #
    }
};

/**
 * 清除数据
 */
@{crud.table.className}InfoDlg.clearData = function() {
    this.@{crud.table.className}InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
@{crud.table.className}InfoDlg.set = function(key, val) {
    this.@{crud.table.className}InfoData[key] = (typeof value == "undefined") ? $("\#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
@{crud.table.className}InfoDlg.get = function(key) {
    return $("\#" + key).val();
}

/**
 * 关闭此对话框
 */
@{crud.table.className}InfoDlg.close = function() {
    parent.layer.close(window.parent.@{crud.table.className}.layerIndex);
}

/**
 * 收集数据
 */
@{crud.table.className}InfoDlg.collectData = function() {
	# for(column in crud.table.columns){ #
	this.set('@{strutils.toLowerCaseFirst(column.columnJavaName)}');
    # } #
}

/**
 * 验证数据是否为空
 */
@{crud.table.className}InfoDlg.validate = function () {
    $('\#InfoForm').data("bootstrapValidator").resetForm();
    $('\#InfoForm').bootstrapValidator('validate');
    return $("\#InfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
@{crud.table.className}InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/add", function(data){
        Feng.success("添加成功!");
        window.parent.@{crud.table.className}.table.refresh();
        @{crud.table.className}InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.@{crud.table.className}InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
@{crud.table.className}InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/update", function(data){
        Feng.success("修改成功!");
        window.parent.@{crud.table.className}.table.refresh();
        @{crud.table.className}InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.@{crud.table.className}InfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("InfoForm", @{crud.table.className}InfoDlg.validateFields);
});