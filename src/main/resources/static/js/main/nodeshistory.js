var historyData=[]
var ip = initIP()
$(function() {
    init();
});
function init(){
    returnZindex();
    initHistory()

}
function initHistory() {
    var userId = sessionStorage.getItem("userId");
    if(userId){
        let records = {
            url:ip+"nodes/user/"+userId,
            method:"GET",
        }
        axios(records).then(data=>{
            console.log("nodes11111",data.data.data.records)
            nodesData = data.data.data.records;
            //找到笔记数据
            for(let b of nodesData){
                var str = b.nodeContent
                var index = str.indexOf('noteClass2'); // 字符出现的位置
                var num = 0; // 这个字符出现的次数
                while(index !== -1) {
                    num++; // 每出现一次 次数加一
                    index = str.indexOf('noteClass2',index + 1); // 从字符串出现的位置的下一位置开始继续查找
                }
                console.log('noteClass2一共出现了' + num + '次');
                b.num = num;

            }
            initNodesTable(nodesData)
        })
    }else{
        alertLayer2("提示","请先登录")
    }

}
function initNodesTable(nodesData) {
    var Titles = [
        {name:'书名',key:'name'},
        {name:'封面',key:'cover'},
        {name:'作者',key:'author'},
        {name:'章节名称',key:'chapter.title'},
        {name:'做笔记次数',key:'num'},
    ]
    var hiddenTitles=[
        {name:'id',key:'id'},
        {name:'bookId',key:'bookId'},
        {name:'章节id',key:'chapter.id'},
        {name:'用户id',key:'userId'},
    ]
    let thHtml="";
    let tBodyHtml ="";
    for(let title of Titles){
        thHtml+='<th>'+title.name+'</th>'
    }
    thHtml+='<th>操作</th>'
    $("#thId").html(thHtml);
    for(let item of nodesData){
        if(item.num>0) {
            var $row2 = JSON.stringify(item).replace(/\"/g, "'");
            tBodyHtml += '<tr>'
            for (let title of Titles) {
                if (title.key === "cover") {
                    tBodyHtml += '<td><img src="' + item.book.cover + '" class="img-class"></td>'
                } else if (title.key === "chapter.title") {
                    console.log(12, item)
                    tBodyHtml += '<td><a onclick="checkBookStatus(' + $row2 + ')" title="' + item.chapter.title + '">' + item.chapter.title + '</a></td>'
                } else if (title.key === "num") {
                    tBodyHtml += '<td>' + item.num + '</td>'
                } else {
                    tBodyHtml += '<td>' + item.book[title.key] + '</td>'

                }
            }
            for (let title of hiddenTitles) {
                if (title.key === "chapter.id") {
                    tBodyHtml += '<td style="display: none">' + item.chapter.id + '</td>'
                } else {
                    tBodyHtml += '<td style="display: none">' + item[title.key] + '</td>'
                }
            }
            var $row = JSON.stringify(item).replace(/\"/g, "'");
            tBodyHtml += '<td><button type="button" class="layui-btn  mb-1" onclick="readBook(' + $row + ')">查看</button>'
            tBodyHtml += '<button type="button" class="layui-btn layui-btn-danger  mb-1" onclick="deleteHistory(' + $row + ')">删除</button>'
            tBodyHtml += '</td></tr>'
        }
    }
    $("#tBodyId").html(tBodyHtml)
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}
function checkBookStatus(item) {
    // href="'+ip+"readChapter.html?chapterId="+item.chapter.id+'"
    let bookMethod={
        url:ip+"/book/"+item.book.id,
        method:"GET"
    }
    axios(bookMethod).then(data=>{
        console.log(data.data)
        if(data.data.data.status==0){
        alertLayer2("提示","该书已下架")
    }else{
        window.location.href=ip+"readChapter.html?chapterId="+item.chapter.id;
    }
})
}
function deleteHistory(row) {
    quesForm("你確定要刪除嗎？",deleteHistoryMethod,row)
}
function readBook(row) {
    let bookMethod={
        url:ip+"/book/"+row.bookId,
        method:"GET"
    }
    axios(bookMethod).then(data=>{
        console.log(data.data)
        if(data.data.data.status==0){
            alertLayer2("提示","该书已下架")
        }else{
            window.location.href=ip+"clickBook.html?id="+data.data.data.id
        }
    })
}
function deleteHistoryMethod(row) {
    let deleteMethod={
        url:ip+"ReadingRecord/"+row.bookId,
        method:"DELETE"
    }
    axios(deleteMethod).then(data=>{
        alertLayer("提示",data.data.msg,closeForm)
    })

}
function closeForm() {
    layer.closeAll();
    init();
}
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}