/// <reference path='jquery.d.ts'/>
/// <reference path='bootstrap.d.ts'/>
/// <reference path='bootstrapTable.d.ts'/>
var rowData = (function () {
    function rowData(age, name) {
        this.age = age;
        this.name = name;
    }
    return rowData;
}());
$(function () {
    $("#dddd").hide();
    var row1 = new rowData(16, 'he');
    var row2 = new rowData(17, 'xiao');
    var row3 = new rowData(18, 'bin');
    var data = [row1, row2, row3];
    console.log(data);
    $('#bt').bootstrapTable({
        striped: true,
        singleSelect: false,
        pagination: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        search: false,
        data: data,
        columns: [{
                field: 'age',
                title: '序号'
            }, {
                field: 'name',
                title: '交易编号'
            }]
    });
    var tdata = $("#bt").bootstrapTable("getData");
    console.log(tdata);
    var row4 = new rowData(19, 'he');
    var row5 = new rowData(20, 'xiao');
    var data2 = [row4, row5];
    var method = ['load', data];
    $("#bt").bootstrapTable('load', data2);
});
//# sourceMappingURL=index.js.map