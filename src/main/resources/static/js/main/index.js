<!--轮播图 end-->
var ip = initIP();
//初始化
$(function() {
    //重新给一个高度给iframe
    reInitFrameHeight();
    //验证是否登录，登录则显示用户信息，否则显示登录信息
    getCarouselItem();
    getNewBook();
    getCategory();
    initSwiper();
    returnZindex();
});

function searchBook() {
    let dom = document.getElementById("bookSearch").value;
    if(!dom.trim()){
        alertLayer2("提示","请输入关键字查询")
    }else{
        let name = encodeURI(dom)
        window.location.href=ip+"search.html?name="+name
    }

}
function reInitFrameHeight() {
    // frame-content
    let screenHeight = window.screen.height;
    var scrollHeight=document.getElementById("main").scrollHeight;
    var scrollHeight2=document.getElementById("frameContent").scrollHeight;
    var dom=$("#frameContent")[0]
    dom.height = scrollHeight+scrollHeight2+"px";
}
//layer的問題，需要改變zindex並且在調用彈出窗口時（登錄和退出時）改變z-index
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}
function showPerson(){
    let s = document.getElementById("showLogout")
    s.style.visibility = ""
}
function hiddenPerson(){
    let s = document.getElementById("showLogout")
    s.style.visibility = "hidden"
}
//初始化swiper的dom
function initSwiper(){
    let topBook={
        url:ip+"book/top",
        method:"GET"
    }
    axios(topBook).then(data=>{
        //這裡字段不能多空格，否則拼接會報錯
        let swiperJson = data.data.data.records;
        let nHtml="";
        if(swiperJson.length>0){
            var keys = Object.keys(swiperJson[0]);
        }
        for(let i=0;i<swiperJson.length;i++){
            let item = swiperJson[i]
            for(let key of keys){
                if(typeof(item[key])=="string"){
                    item[key] = item[key].trim()
                }
            }

            nHtml+='<div class="swiper-slide"><img onclick=swiperItemClick('+JSON.stringify(item)+') src="'+item.cover+'" class="img2" title="'+item.name+'">'+item.name+'</div>'
        }
        $("#wrapperId").html(nHtml)
        getSwiper(swiperJson);
    })

}
function getSwiperDesc(item){
    let nHtml = "";
    nHtml+='<div class="bookname bb-2 center">'+item.name+'</div>'
    nHtml+='<div class="bb-1">'+"作者:"+item.author+'</div>'
    nHtml+='<div class="bb-1">'+"簡介:"+item.synopsis+'</div>'
    nHtml+='<div class="bb-1 center"><button type="button" class="layui-btn layui-btn-primary" ><a href="'+ip+"clickBook.html?id="+item.id+'">'+"書籍詳情"+'</a></button></div>'
    $("#bookDesc").html(nHtml)
}

//將swiper顯示出來
function getSwiper(swiperJson){
    var mySwiper = new Swiper('.swiper-container', {
        //速度
        speed:300,
        autoplay : {
            //延遲
            delay:1000
        },
        //初始化爲initialSlide+1=下標
        initialSlide:-1,
        //是否一直循環
        loop:true,
        effect : 'coverflow',
        //同时显示的slides数量
        slidesPerView : 3,
        //active slide会居中
        centeredSlides : true,
        //使得第一个和最后一个Slide 始终贴合边缘。
        centeredSlidesBounds: true,
        // pagination :{
        //   el: '.swiper-pagination',
        //   clickable :true,
        // },
        on:{
            //監聽自動切換  this.realIndex是當前的索引
            autoplay:function(){
                getSwiperDesc(swiperJson[this.realIndex])
            },
        }
    })
}
//點擊swiper（點擊一本書）
function swiperItemClick(item){
    getSwiperDesc(item)
}


function testId(){
    $("#test").toggleClass("blue")
}
function clickTitle(){
    alert("即将前往首页")
}
function onmouseoverBook(item){

    //显示书籍的相关信息到下面
}
//获取轮播图数据axios得到json数据
function clickBook(row) {
    window.location.href = ip+"clickBook.html?id="+row.bookId
}
function getCarouselItem(){

    //typeId为3
    let typeBook={
        url:ip+"book-type/3",
        method:"GET",
    }
    axios(typeBook).then(data=>{
        let bookJson = data.data.data.records;

        var cHtml="";
        for(let i=1;i<=bookJson.length;i++){
            let item = bookJson[i-1];
            var $row = JSON.stringify(item).replace(/\"/g,"'");
            //开始
            if(i%5==1){
                cHtml+='<div><div class="book"><img src="'+item.book.cover+'" class="img1" title="'+item.book.name+'" onclick="clickBook('+$row+')" onmouseover="onmouseoverBook('+$row+')">'+item.book.name+'</div>'
            }
            //结束
            if(i%5==0){
                cHtml+='<div class="book"><img src="'+item.book.cover+'" class="img1" title="'+item.book.name+'" onclick="clickBook('+$row+')" onmouseover="onmouseoverBook('+$row+')">'+item.book.name+'</div></div>'
            }
            //中间
            if(i%5!=1&&i%5!=0){
                cHtml+='<div class="book"><img src="'+item.book.cover+'" class="img1" title="'+item.book.name+'" onclick="clickBook('+$row+')" onmouseover="onmouseoverBook('+$row+')">'+item.book.name+'</div>'
            }
        }
        $("#itemId").html(cHtml)
        layui.use('carousel', function(){
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#carouselId'
                ,width: '100%' //设置容器宽度
                ,arrow: 'none' //始终显示箭头
                // ,anim: 'updown' //切换动画方式
            });
        });
    })

}
layui.use('element', function(){
    var element = layui.element;
});
function getNewBook(){
    let nHtml = "";
    let newBookrem={
        url:ip+"book/newbook-recommend",
        method:"GET"
    }
    axios(newBookrem).then(data=>{
        let newBookJson=data.data.data.records;
        for(let i=0;i<newBookJson.length;i++){
            let item = newBookJson[i];
            let itemBook = newBookJson[i].name;
            if(item.name.length>8){
                itemBook = itemBook.substring(0,8)+"..."
            }
            nHtml+='<div class="newBook-div bb">'
            nHtml+='<span class="float-left">'+'<a href="'+ip+"clickBook.html?id="+item.id+'"title="'+item.name+'">'+itemBook+'</a>'+'</span>';
            nHtml+='<p class="float-right">'+item.author+'</p>'
            nHtml+='</div>'
        }
        $("#newBook").html(nHtml);
    })
}
function getCategory(){
    let allType = {
        url:ip+"type",
        method:"GET",
    }
    axios(allType).then(data=>{
        let categoryJson = data.data.data.records;
        let nHtml = ""
        for(let i=1;i+1<=categoryJson.length;i++){
            let item = categoryJson[i-1];
            let item2 = categoryJson[i];
            i++;
            if(parseInt((i-1)/2)%2==0){
                nHtml+='<div class="b1">'
            }else{
                nHtml+='<div class="b2">'
            }
            //TODO  需要修改分类的地址
            nHtml+='<div class="cateItem center">'+'<a href="'+ip+"menu.html?id="+item.id+'">'+item.typeName+'</a>'+'</div>';
            nHtml+='<div class="cateItem center">'+'<a href="'+ip+"menu.html?id="+item2.id+'">'+item2.typeName+'</a>'+'</div>';
            nHtml+='</div>'
            //为奇数且为最后一个节点时
            if(i+1==categoryJson.length){
                let item3 = categoryJson[i];
                nHtml+='<div class="b1">'
                nHtml+='<div class="cateItem center">'+'<a href="'+ip+"menu.html?id="+item3.id+'">'+item3.typeName+'</a>'+'</div>';
                nHtml+='</div>'
            }
        }
        $("#category").html(nHtml);
    })
    // let categoryJson=["历史军事","武侠修真","其他类型","网游动漫","都市言情","都市言情","都市言情","都市言情","玄幻魔法"]

}