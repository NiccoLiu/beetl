/**
 * @{crud.table.remarks} 管理
 */
var @{crud.table.className} = {
    id: "@{crud.table.className}Table",
    seItem: null,
    table: null,
    layerIndex: -1
};

@{crud.table.className}.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        # for(column in crud.table.columns){ #
        	# if( columnLP.last ) { #
        {title: '@{column.remarks}', field: '@{strutils.toLowerCaseFirst(column.columnJavaName)}', visible: true, align: 'center', valign: 'middle'}
        	# } else if( column.pk ) { #
    	{title: '@{column.remarks}', field: '@{strutils.toLowerCaseFirst(column.columnJavaName)}', visible: false, align: 'center', valign: 'middle'},
        	# } else { #
     	{title: '@{column.remarks}', field: '@{strutils.toLowerCaseFirst(column.columnJavaName)}', visible: true, align: 'center', valign: 'middle'},
    		# } #
        # } #
    ];
};

@{crud.table.className}.check = function () {
    var selected = $('\#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	@{crud.table.className}.seItem = selected[0];
        return true;
    }
};

@{crud.table.className}.openAdd@{crud.table.className} = function () {
    var index = layer.open({
        type: 2,
        title: '添加@{crud.table.remarks}',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/@{strutils.toLowerCaseFirst(crud.table.className)}_add'
    });
    this.layerIndex = index;
};

@{crud.table.className}.open@{crud.table.className}Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改@{crud.table.remarks}',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/@{strutils.toLowerCaseFirst(crud.table.className)}_edit/' + @{crud.table.className}.seItem.id
        });
        this.layerIndex = index;
    }
};

@{crud.table.className}.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/delete", function (data) {
                Feng.success("删除成功!");
                @{crud.table.className}.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", @{crud.table.className}.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否删除通知 " + @{crud.table.className}.seItem.title + "?", operation);
    }
};

@{crud.table.className}.search = function () {
    var queryData = {};
    # for(column in crud.table.searchColumns){ #
    queryData['@{strutils.toLowerCaseFirst(column.columnJavaName)}'] = $("\#@{strutils.toLowerCaseFirst(column.columnJavaName)}").val();
    # } #
    @{crud.table.className}.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = @{crud.table.className}.initColumn();
    var table = new BSTable(@{crud.table.className}.id, "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}/list", defaultColunms);
    @{crud.table.className}.table = table.init();
});
