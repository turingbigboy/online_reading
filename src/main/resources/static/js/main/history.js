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
            url:ip+"ReadingRecord/"+userId,
            method:"GET",
        }
        axios(records).then(data=>{
            console.log("阅读记录",data.data.records)
            historyData = data.data.records;
            initHistoryTable(historyData)
        })
    }else{
        alertLayer2("提示","请先登录")
    }

}
function initHistoryTable(historyData) {
    var Titles = [
        {name:'书名',key:'bookName'},
        {name:'封面',key:'bookCover'},
        {name:'作者',key:'bookAuthor'},
        {name:'章节名称',key:'chapterName'},
        {name:'阅读时间',key:'redcordTime'},
    ]
    var hiddenTitles=[
        {name:'id',key:'id'},
        {name:'bookId',key:'bookId'},
        {name:'章节id',key:'chapterId'},
        {name:'用户id',key:'userId'},
    ]
    let thHtml="";
    let tBodyHtml ="";
    for(let title of Titles){
        thHtml+='<th>'+title.name+'</th>'
    }
    thHtml+='<th>操作</th>'
    $("#thId").html(thHtml);
    for(let item of historyData){
        tBodyHtml+='<tr>'
        for(let title of Titles){
            if(title.key=="bookCover"){
                tBodyHtml+='<td><img src="'+item[title.key]+'" class="img-class"></td>'
            }
            else if(title.key=="chapterName"){
                console.log(12,item)
                tBodyHtml+='<td><a href="'+ip+"readChapter.html?chapterId="+item.chapterId+'"title="'+item.name+'">'+item[title.key]+'</a></td>'
            }
            else{
                tBodyHtml+='<td>'+item[title.key]+'</td>'
            }
        }
        for(let title of hiddenTitles){
            tBodyHtml+='<td style="display: none">'+item[title.key]+'</td>'
        }
        var $row = JSON.stringify(item).replace(/\"/g,"'");
        tBodyHtml+='<td><button type="button" class="layui-btn  mb-1" onclick="readBook('+$row+')">查看</button>'
        tBodyHtml+='<button type="button" class="layui-btn layui-btn-danger  mb-1" onclick="deleteHistory('+$row+')">删除</button>'
        tBodyHtml+='</td></tr>'
    }
    $("#tBodyId").html(tBodyHtml)
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
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
        url:ip+"ReadingRecord/"+row.id,
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