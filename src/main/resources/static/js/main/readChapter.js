var key;
var value;  //chapterId
var bookId;
var sort;   //当前章节序号
var findChpaterSort;
var history;  //历史
var contentTxt;  //文本形式的文章数据
var ip = initIP();
var content = "我是一条小小的评论"
var htmlSend;
var contentRange;
var bj;
var currentChild;  //当前的子节点

$(function() {
    init();
    initContent();
    t();
    initWidth();
    watch()
    notesShow()

    // t2();
});
function watch() {

}

function ajaxAddNotes() {

    //笔记外面的样式
    var selectionContents = contentRange.extractContents();
    var spanOut = document.createElement("span");
    spanOut.className='noteClass'
    spanOut.appendChild(selectionContents);
    contentRange.insertNode(spanOut);


    //笔记的内容
    bj =  document.getElementById("notesArea").value
    var selectionContents = contentRange.extractContents();
    var span = document.createElement("span");
    var spanBJ = document.createTextNode(bj);
    span.className='noteClass2 hidden'
    // spanBJ.className='spanBJClass'
    span.appendChild(spanBJ);
    selectionContents.appendChild(span)
    contentRange.insertNode(selectionContents);

    //ajax保存html
    let allhtml = $('#chapterContent').html()
    let params={
        bookId:bookId,
        userId:Number(sessionStorage.getItem("userId")),
        chapterId:Number(value),
        nodeContent:allhtml,

    }
    let bgMethod={
        url:ip+"nodes",
        method:"POST",
        data:params
    }
    axios(bgMethod).then(data=>{
        alertLayer2("提示",data.data.msg)
    })
}
function getNotesData(range) {
    let bj = document.getElementById("notesArea").value
    console.log("笔记内容",bj)
    contentRange = range
    $("#notesArea")[0].style.width="80%"
    $("#notesArea")[0].style.marginLeft="10%"
    $("#notesArea")[0].style.height="60px"


}
function validateNothing() {

}
function clickContent(range){
    let html = '<div><textarea id="notesArea"></textarea></div>'
    alertcomfirm2("addNotesContent","添加笔记",html,"updateUserVerify",null,validateNothing,ajaxAddNotes,range,getNotesData)

}
function notesShow(){
    $('.contenttext').mouseover(function(){
        $('.noteClass').mouseover(function(){
            console.log("in")
            //显示
            // 找到该span的子节点
            let dom = $('.noteClass:hover')
            for(let i=0;i<dom.length;i++){
                console.log('111111aaaaaaa',dom[i].nextElementSibling)
                //显示子节点
                currentChild = dom[i].nextElementSibling;
                currentChild.className='noteClass2 show'
                currentChild.style.left =dom[i].offsetLeft+"px"
                currentChild.style.top = dom[i].offsetTop+20+"px"
            }

        })
        $('.noteClass').mouseout(function(){
            console.log("out")
            // document.getElementById("contentId").style.visibility = "hidden"
            let dom = $('.noteClass')
            for(let i=0;i<dom.length;i++){
                console.log('111111aaaaaaa',dom[i].nextElementSibling)
                //显示子节点
                currentChild = dom[i].nextElementSibling;
                currentChild.className='noteClass2 hidden'
                // currentChild.style.display="none"
            }
        })

    })

}
function getMousePos(event) {
    var e = event || window.event;
    return {'x':e.clientX,'y':e.clientY}
}
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}
function initWidth() {
    let screenWidth = window.screen.width;
    let innerWidth = screenWidth*62/100;
    console.log("innerWidth",innerWidth)
    let inner = document.getElementById("chapterContent")
    // inner.style.width = innerWidth+"px";
    inner.style.marginLeft = screenWidth*10/100+"px";
    inner.style.marginRight = innerWidth*10/100+"px";
    inner.style.overflow='auto'
}

function removeHTMLTag(str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
    return str;
}
//去掉空格
// function Trim(str)
// {
//     return str.replace(/(^\s*)|(\s*$)/g, "");
// }
function initContent() {
    let href = window.location.href;
    let params = href.split("?")[1]
    let param = params.split("=");
    key = param[0];
    value = param[1];
    console.log("value",value)

    //根据value得到的chapterId获取章节信息
    // chapter/read/{userId:\\d+}/{chapterId:\\d+}
    let chapterMethod={
        url:ip+"chapter/detail/"+value,
        method:"GET",

    }
    axios(chapterMethod).then(data=>{
        let item = data.data.data;
        console.log("chapterMethod",data)
        console.log(666,data.data.data.content)
        contentTxt = data.data.data.content;
        contentTxt = removeHTMLTag(contentTxt)
        // contentTxt = Trim(contentTxt)
        bookId = item.bookId;
        addHistory(value,bookId);

    })

}
function init(){
    returnZindex();
}

function addHistory(chapterId,bookId) {
    if(chapterId){
        let userId = sessionStorage.getItem("userId")||0
        let historyMethod={
            url:ip+"chapter/read/"+userId+"/"+bookId,
            method:"GET",
            params:{
                chapterId:chapterId
            }
        }
        axios(historyMethod).then(data=>{
            console.log("read接口",data)
            let item = data.data.data;
            findChpaterSort = data.data.data.page;
            sort = item.sort;

            htmlSend='<div>';
            if(item.content.indexOf('content-title')==-1){
                htmlSend+='<h1 class="center" id="content-title">'+item.title+'</h1>'
            }
            htmlSend+='<div>'+item.content+'</div>'
            htmlSend+='</div>'
            htmlSend+='<div id="showBjDiv" style="display: none;" ></div>'

            if(item.content.indexOf('content-buttons')==-1){
                htmlSend+='<div class="center" id="content-buttons">'
                htmlSend+='<button class="layui-btn layui-btn-radius layui-btn-warm" onclick="preChapter()">上一章</button>'
                htmlSend+='<button class="layui-btn layui-btn-radius layui-btn-warm"  onclick="toBook()">返回书籍</button>'
                htmlSend+='<button class="layui-btn layui-btn-radius layui-btn-warm"  onclick="nextChapter()">下一章</button>'
                htmlSend+='</div>'
                htmlSend+='</div>'
                htmlSend+='</div>'
            }
            $("#chapterContent").html(htmlSend);
            showMark(data)
        })
    }
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}
function t(){
    $('.contenttext').mouseup(function(){
        // SetToBold();
        var range = window.getSelection().getRangeAt(0);
        var container = range.startContainer;
        var txt = window.getSelection?window.getSelection():document.selection.createRange().text;
        if(txt){
            let w = window.getSelection();
            const start = {
                node: range.startContainer,
                offset: range.startOffset
            };
            const end = {
                node: range.endContainer,
                offset: range.endOffset
            };
            CssTxt(txt,start.offset,end.offset);
            let length = end.offset-start.offset
            var rect = range.getBoundingClientRect();
            var x = rect.left, y = rect.top;var height = rect.height;var top = rect.top;
            let x1 = (rect.left+rect.width)/2
            //y1不是一行的话要减掉一个行高
            let y1 = (rect.top+rect.y)
            if(length!=0){
                showBj(rect,length,txt,range,start,end);
            }else{
                hiddenBj();
            }
        }

    })

}
function CssTxt(txt,start,end){
    let allTxt = txt.anchorNode.wholeText;
    if(allTxt){
        //分割成数组，后面可以根据开始和结束的位置得到数据
        allTxt = allTxt.split('');
        var chooseArr = [];
        var chooseTxt = [];
        for(let i=start;i<end;i++){
            chooseArr.push(allTxt[i]);
        }
        chooseTxt = chooseArr.join('')
    }

}
function mouseupBj(){

}
function hiddenBj(){
    document.getElementById("showBjDiv").style.display = "none"
}
function showBj(rect,length,txt,range,start,end){
    let x = rect.left;
    // let y = (rect.top+rect.y)
    let top = rect.top;
    let height = rect.height;
    let nHtml = "";
    //top-height/几行
    //一行20 每增加一行增加40px
    let heightNum = (height+20)/40
    //191为y第一行的起始位置
    let y = rect.y
    // let y=top-height/heightNum;
    // let y = rect.height*heightNum;
    nHtml+='<div id="showBjDiv" class="showBjDiv" style="position:fixed;left:'+x+"px"+';top:'+y+"px"+'">'
    nHtml+='<span onclick=addBj() class="span-bj" id="addNodes">做笔记</span>'
    nHtml+= '<span  class="span-bj" id="mark">马克笔</span>';
    nHtml+='</div>'
    $("#showBjDiv").html(nHtml)

    let userId = sessionStorage.getItem("userId")||0
    if(userId!=0){
        document.getElementById("showBjDiv").style.display = ""
        document.getElementById("mark").addEventListener("click", function(){
            if(end-start!=0){
                if(sessionStorage.getItem("userId")!=""&&sessionStorage.getItem("userId")!=null){
                    let endNode1 = end.node.data
                    let startNode1 = start.node.data
                    let t = endNode1.replace(/<[^>]+>/g,"");
                    clickMark(range)
                }else{
                    alertLayer2("提示","请登录后编辑")
                }
            }
        });


        document.getElementById("addNodes").addEventListener("click", function(){
            addNodes(range)
        });
    }

}
function ajaxAddBj() {
    let allhtml = $('#chapterContent').html()
    let params={
        bookId:bookId,
        userId:Number(sessionStorage.getItem("userId")),
        chapterId:Number(value),
        nodeContent:allhtml,
    }
    let bgMethod={
        url:ip+"nodes",
        method:"POST",
        data:params
    }
    axios(bgMethod).then(data=>{
        alertLayer2("提示",data.data.msg)
    })
}
function addNodes(range){

    clickContent(range)
    hiddenBj();

}
function addBj(){

    hiddenBj();
}

function showMark(data) {
    console.log(data.data.data)
    let dataAll = data.data.data;
    let content = dataAll.content
    let nodes = dataAll.nodes;
    if(nodes){
        nodes.forEach(item=>{
            let start = item.nodeBeginLocation;
            let end = item.nodeEndLocation;
            var span = document.createElement("span");
            span.className='mark-class'
            var range = document.createRange();
            let arr =  contentTxt.split("");
            let newArr = []
            for(let i = start;i<=end;i++){
                newArr.push(arr[i])
            }
            //     let newStr = newArr.join('')
            //     const selection = window.getSelection();
            //     let div = document.getElementById("chapterContent");
            //     let rangeObj =  document.createRange();
            //    let t = rangeObj.selectNodeContents(div);
            //    var textNode = div.firstChild;
            //     // var rangeObj = document.createRange();
            //    // rangeObj.setStart(textNode,start);
            //    //  rangeObj.setEnd(textNode,end);
            // textNode.setStartBefore(newStr);
            // textNode.setEndAfter(newStr);
            var oP1 = document.getElementById("chapterContent");
            var child = oP1.childNodes[0];
            var oRange = document.createRange();
            oRange.setStart(child, 1);
            oRange.setEnd(child, 3);
            var oSpan = document.createElement("span");
            oRange.selectNodeContents(oP1); //range代表srcDiv的内容

            var selectionContents = oRange.extractContents();
            var oSpan = document.createElement("span");
            oSpan.className='mark-class'
            oSpan.appendChild(selectionContents);
            console.log("range",oRange,selectionContents)
            oRange.insertNode(oSpan);
            console.log(oSpan)



            //
            // //TODO 这里改为找到该元素的范围
            // oSpan.appendChild(document.createTextNode("Inserted text"));
            //
            // oSpan.className='mark-class'
            //
            //
            // // console.log("range",range,selectionContents)
            // // range.insertNode(span);
            // // console.log(span)
            // hiddenBj();
            // oRange.setStart(child, 1);
            // oRange.setEnd(child, 2);
            // var selectionContents = oRange.extractContents();
            // // var span = document.createElement("span");
            // oSpan.className='mark-class'
            // oSpan.appendChild(selectionContents);
            // oRange.insertNode(oSpan);
            // clickMark(textNode)
        })
    }


}
function encodeHtml(str){
    var encodedStr = "" ;
    if (str=="") return encodedStr ;
    else {
        for (var i = 0 ; i < str.length ; i ++){
            encodedStr += "&#" + str.substring(i, i + 1).charCodeAt().toString(10) + ";" ;
        }
    }
    return encodedStr ;
}
function htmlEncode(html){
    //1.首先动态创建一个容器标签元素，如DIV
    var temp = document.createElement ("div");
    //2.然后将要转换的字符串设置为这个元素的innerText或者textContent
    (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
    //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
    var output = temp.innerHTML;
    temp = null;
    return output;
}
function clickMark(range){
    var selectionContents = range.extractContents();
    var span = document.createElement("span");
    span.className='mark-class'
    span.appendChild(selectionContents);
    console.log("range",range,selectionContents)
    range.insertNode(span);
    let allhtml = $('#chapterContent').html()
    let params={
        bookId:bookId,
        userId:Number(sessionStorage.getItem("userId")),
        chapterId:Number(value),
        nodeContent:allhtml,

    }
    let bgMethod={
        url:ip+"nodes",
        method:"POST",
        data:params
    }
    axios(bgMethod).then(data=>{
        alertLayer2("提示",data.data.msg)
    })
    console.log("bgMethod",bgMethod)
    console.log(span)
    hiddenBj();
}
function SetToBold () {
    var range = window.getSelection().getRangeAt(0);
    var selectionContents = range.extractContents();
    var span = document.createElement("span");
    // span.className='mark-class'
    span.appendChild(selectionContents);
}

function preChapter(){
    let userId = sessionStorage.getItem("userId")||0
    let method={
        url:ip+"chapter/read/"+userId+"/"+bookId,
        method:"GET",
        params:{
            page:findChpaterSort-1,
            pageSize:1
        }
    }
    axios(method).then(data=>{
        console.log("****",data)
        if(data.data.code==403||findChpaterSort-1==0){
            alertLayer2("提示","没有上一章")
        }else{
            value = data.data.data.id
            window.location.href=ip+"readChapter.html?chapterId="+value
        }

    })
}
function nextChapter(){
    let userId = sessionStorage.getItem("userId")||0
    let method={
        url:ip+"chapter/read/"+userId+"/"+bookId,
        method:"GET",
        params:{
            page:findChpaterSort+1,
            pageSize:1
        }
    }
    axios(method).then(data=>{
        if(data.data.code==403){
            alertLayer2("提示",data.data.msg)
        }else{
            value = data.data.data.id
            window.location.href=ip+"readChapter.html?chapterId="+value
        }

    })
}
function toBook(){
    window.location.href=ip+"clickBook.html?id="+bookId
}