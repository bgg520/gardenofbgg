/// <reference path='jquery.d.ts'/>
/// <reference path='bootstrap.d.ts'/>
/// <reference path='bootstrapTable.d.ts'/>

class rowData {
	public age:number;
	public name:string;

	constructor(age:number, name:string){
		this.age = age;
		this.name = name;
	}
}

$(function () {
    debugger;
    $("#dddd").hide();

    let row1 = new rowData(16, 'he');
    let row2 = new rowData(17, 'xiao');
    let row3 = new rowData(18, 'bin');

    let data:rowData[] = [row1, row2, row3];
    console.log(data);
    $('#bt').bootstrapTable({
		striped: true,                      //是否显示行间隔色
		singleSelect: false,
		pagination: true, //分页
		pageNumber:1,                       //初始化加载第一页，默认第一页
		pageSize: 10,                       //每页的记录行数（*）
		pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
		search: false, //显示搜索框
		data: data,
		columns: [{
			field: 'age',
			title: '序号'
		}, {
			field: 'name',
			title: '交易编号'
		}]
    })
    debugger;
    data.pop();
    console.log(data);
    let method:any = ['load', data];
    $("#bt").bootstrapTable(method);
});