var ip = initIP();
$(function () {
    checkAdmin().then((data) => {
        if(data.data.data){
        init()
    }
else{
        alertLayer("提示", "你没有权限访问该页面", closetoIndex);
    }
});
});
function checkAdmin() {
    let checkAdminMethod = {
        url: ip + "user/check_identity",
        method: "GET",
        params: {}
    }
    let promise = axios(checkAdminMethod).then(data => {
        return data;
})
    return promise;
}

function closetoIndex() {
    window.location.href = ip + "index.html"
}

function init() {
    returnZindex();
    initTab();
    initTable();
    getAllBookTypes();

}

//拼接Book   由於時間控件的問題，這裡必須用拼接組裝js，否則會出现laydate闪现等bug
function pjBook() {
    var nHtml = '<div class="layui-form-item">'
    nHtml += '<input type="file" id="file" name="file" class="chooseImg"><br/><img id="img">'
    nHtml += '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">id</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">书籍名称</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="name" id="name" lay-verify="name" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">作者</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="author" id="author" lay-verify="author" placeholder="请输入作者" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">出版社</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="publishingHouse" id="publishingHouse" lay-verify="publishingHouse" placeholder="请输入出版社" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">简介</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="synopsis" id="synopsis" lay-verify="synopsis" placeholder="请输入简介" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<input type="text"  style="display: none"name="status" id="status" autocomplete="off" class="layui-input">'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">出版时间</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += ' <input type="text" class="layui-input" name="publicationTime" id="publicationTime" placeholder="yyyy-MM-dd HH:mm:ss">'
    nHtml += '</div>'

    return nHtml;
}

function pjUser() {
    var nHtml = '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">id</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += ' <input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml += ' </div>'
    nHtml += '</div>'
    nHtml += '<input type="file" id="file" name="file" class="chooseImg"><br/><img id="img">'
    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">用戶名</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="name" id="name"  placeholder="请输入用戶名" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'
    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">密码</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += ' <input type="password" name="password" id="password" placeholder="请输入密码" autocomplete="off" class="layui-input">'
    nHtml += ' </div>'
    nHtml += '</div>'
    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">账号</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="account" id="account"  placeholder="请输入账号" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'
    // nHtml+='<div class="layui-form-item">'
    // nHtml+='<label class="layui-form-label">图片</label>'
    // nHtml+='<div class="layui-input-inline">'
    // nHtml+='<input type="text" name="icon" id="icon" placeholder="请输入图片" autocomplete="off" class="layui-input">'
    // nHtml+='</div>'
    // nHtml+='</div>'
    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">身份</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="number" name="identity" id="identity" placeholder="请输入身份" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'
    return nHtml;
}

function pjType() {
    var nHtml = '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">id</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += ' <input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml += ' </div>'
    nHtml += '</div>'
    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">类型</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="typeName" id="typeName" lay-verify="typeName" placeholder="请输入类型名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'


    return nHtml;
}

function pjChapter(row) {
    var nHtml = '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">id</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">书籍id</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="bookId" value="' + row.id + '" readonly id="bookId" lay-verify="bookId" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">书籍名称</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="bookName" value="' + row.bookName + '" readonly id="bookName" lay-verify="bookName" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    // nHtml+='<div class="layui-form-item" style="display: none">'
    // nHtml+= '<label class="layui-form-label">字数</label>'
    // nHtml+='<div class="layui-input-inline">'
    // nHtml+='<input type="number" name="numberWorders" id="numberWorders" placeholder="请输入字数" autocomplete="off" class="layui-input">'
    // nHtml+='</div>'
    // nHtml+='</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节标题</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="title" id="title" lay-verify="title" placeholder="请输入章节标题" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节序号</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="number" name="sort" id="sort" lay-verify="sort" placeholder="请输入章节序号" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节内容</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<textarea  name="content" id="content" ></textarea>'
    nHtml += '</div>'
    nHtml += '</div>'

    return nHtml;
}

function pjUpdateChapter(row) {
    var nHtml = '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">id</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item" style="display: none">'
    nHtml += '<label class="layui-form-label">书籍id</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="bookId" value="' + row.id + '" readonly id="bookId" lay-verify="bookId" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">书籍名称</label>'
    nHtml += '<div class="layui-input-block">'
    nHtml += '<input type="text" name="bookName" value="' + row.bookName + '" readonly id="bookName" lay-verify="bookName" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    // nHtml+='<div class="layui-form-item" style="display: none">'
    // nHtml+= '<label class="layui-form-label">字数</label>'
    // nHtml+='<div class="layui-input-inline">'
    // nHtml+='<input type="number" name="numberWorders" id="numberWorders" placeholder="请输入字数" autocomplete="off" class="layui-input">'
    // nHtml+='</div>'
    // nHtml+='</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节标题</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="text" name="title" id="title" lay-verify="title" placeholder="请输入章节标题" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节序号</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<input type="number" name="sort" id="sort" lay-verify="sort" placeholder="请输入章节序号" autocomplete="off" class="layui-input">'
    nHtml += '</div>'
    nHtml += '</div>'

    nHtml += '<div class="layui-form-item">'
    nHtml += '<label class="layui-form-label">章节内容</label>'
    nHtml += '<div class="layui-input-inline">'
    nHtml += '<div  name="content" id="content" ><p>' + row.content + '</p></div>'
    nHtml += '</div>'
    nHtml += '</div>'

    return nHtml;
}

var editor2;

function openEdit() {
    var E = window.wangEditor
    editor2 = new E('#content')
    editor2.customConfig.menus = [  //菜单配置，不要可以直接去掉这个默认全部功能都有如下图
        'head',
        'bold',
        'italic',
        'underline'
    ]
    editor2.create()
}

function initTable() {
    let params = {
        page: 1,
        pageSize: 3
    }
    initBookTable(params);
    initUserTable(params);
    initTypeTable(params);
}

function clickImage() {
    layui.use('upload', function () {
        var $ = layui.jquery
            , upload = layui.upload;
        upload.render({
            elem: '#icon'
            // ,url: 'https://httpbin.org/post' //改成您自己的上传接口
            , auto: false
            // //,multiple: true
            // ,done: function(res){
            //     layer.msg('上传成功');
            // }
        });
    })

}

// function clickImage() {
//     layui.use('upload', function(){
//         var upload = layui.upload;
//
//         //执行实例
//         var uploadInst = upload.render({
//             elem: '#icon' //绑定元素
//             ,url: '/upload/' //上传接口
//             ,done: function(res){
//                 //上传完毕回调
//                 console.log(res)
//             }
//             ,error: function(){
//                 //请求异常回调
//             }
//         });
//     });
// }

function getTablePageParam(curr, limit) {
    if (curr && limit) {
        let params = {
            pageSize: limit,
            page: curr
        }
        initBookTable(params)
    } else {
        return false;
    }
}

function getUserPageParam(curr, limit) {
    if (curr && limit) {
        let params = {
            pageSize: limit,
            page: curr
        }
        initUserTable(params)
    } else {
        return false;
    }
}

function getTypeParam(curr, limit) {
    if (curr && limit) {
        let params = {
            pageSize: limit,
            page: curr
        }
        initTypeTable(params)
    } else {
        return false;
    }
}

function initTypeTable(params) {
    let getType = {
        url: ip + "type",
        method: "GET",
        params: params
    }
    axios(getType).then(data => {
        initPage("typePage", getTypeParam, data.data.data.total, params.page,params.pageSize);
    var typeJson = data.data.data.records;
    var typeTitle = [
        {name: 'id', key: 'id'},
        {name: '分类名称', key: 'typeName'},
    ]
    if (typeJson) {
        var keys = Object.keys(typeJson[0]);
    }
    let thHtml = "";
    let tBodyHtml = "";
    for (let title of typeTitle) {
        thHtml += '<th>' + title.name + '</th>'
    }
    thHtml += '<th>操作</th>'
    $("#typeThId").html(thHtml);
    for (let item of typeJson) {
        tBodyHtml += '<tr>'
        for (let title of typeTitle) {
            tBodyHtml += '<td>' + item[title.key] + '</td>'
        }
        var $row = JSON.stringify(item).replace(/\"/g, "'");
        tBodyHtml += '<td><button type="button" class="layui-btn layui-btn-normal float-left mb-1" onclick="updateType(' + $row + ')" >修改</button>'
        tBodyHtml += '<button type="button" class="layui-btn layui-btn-danger  mb-1" onclick="deleteType(' + $row + ')">刪除</button></td>'
        tBodyHtml += '</tr>'
    }
    $("#typeBodyId").html(tBodyHtml)
})
}

function initBookTable(params) {
    let getBook = {
        url: ip + "book",
        method: "GET",
        params: params
    }
    axios(getBook).then(data => {
        initPage("tablePage", getTablePageParam, data.data.data.total, params.page,params.pageSize);
    var bookJson = data.data.data.records;
    var bookTitle = [
        {name: 'id', key: 'id'},
        {name: '书籍名称', key: 'name'},
        {name: '作者', key: 'author'},
        {name: "出版社", key: 'publishingHouse'},
        {name: "出版時間", key: 'publicationTime'},
        {name: '简介', key: 'synopsis'},
        // {name:'字数',key:'numberWorders'},
        {name: '封面', key: 'coverImg'},
        {name: '封面路径', key: 'cover'},
        {name: '状态', key: 'status'},
        {name: '创建时间', key: 'createTime'},
    ]
    if (bookJson) {
        var keys = Object.keys(bookJson[0]);
    }
    let thHtml = "";
    let tBodyHtml = "";
    for (let title of bookTitle) {
        if (title.key == "id") {
            thHtml += '<th class="small">' + title.name + '</th>'
        } else if (title.key == "name") {
            thHtml += '<th class="middle">' + title.name + '</th>'
        } else if (title.key == "cover") {

        } else if (title.key == "coverImg") {
            thHtml += '<th style="min-width: 60px">' + title.name + '</th>'
        } else {
            thHtml += '<th>' + title.name + '</th>'
        }
    }
    thHtml += '<th style="width:550px">操作</th>'
    $("#thId").html(thHtml);
    for (let item of bookJson) {
        console.log("test12", item)
        tBodyHtml += '<tr>'
        for (let title of bookTitle) {
            if (title.key == "id") {
                tBodyHtml += '<td class="small">' + item[title.key] + '</td>'
            } else if (title.key == "name") {
                tBodyHtml += '<td class="middle">' + item[title.key] + '</td>'
            } else if (title.key == "cover") {
                tBodyHtml += '<td style="display: none">' + item[title.key] + '</td>'
            } else if (title.key == "coverImg") {
                tBodyHtml += '<td><img src="' + item.cover + '" class="table-img"></td>'
            } else if (title.key == "status") {
                if (item[title.key] == 0) {
                    tBodyHtml += '<td>下架</td>'
                } else {
                    tBodyHtml += '<td>上架</td>'
                }
            } else {
                tBodyHtml += '<td>' + item[title.key] + '</td>'
            }
        }
        var $row = JSON.stringify(item).replace(/\"/g, "'");
        tBodyHtml += '<td><button type="button" class="layui-btn layui-btn-normal float-left mb-1" onclick="updateBook(' + $row + ')" >修改</button>'
        // tBodyHtml+='<button type="button" class="layui-btn mb-1" onclick="uploadChapter('+$row+')">新增章节</button>'
        // tBodyHtml+='<button type="button" class="layui-btn mb-1" onclick="updateChapter('+$row+')">修改章节</button>'
        tBodyHtml += '<button type="button" class="layui-btn mb-1" onclick="showChapters(' + $row + ')">查看章节列表</button>'
        tBodyHtml += '<button type="button" class="layui-btn mb-1" onclick="typeBook(' + $row + ')">分类</button>'
        // tBodyHtml+='<button type="button" class="layui-btn layui-btn-danger mb-1" onclick="deleteChapter('+$row+')">删除章节</button>'
        if (item.status == 0) {
            tBodyHtml += '<button type="button" class="layui-btn  mb-1" onclick="enableBook(' + $row + ')">上架</button>'
        } else {
            tBodyHtml += '<button type="button" class="layui-btn  mb-1" onclick="disableBook(' + $row + ')">下架</button>'
        }
        // tBodyHtml += '<button type="button" class="layui-btn layui-btn-danger  mb-1" onclick="deleteBook(' + $row + ')">刪除</button></td>'
        tBodyHtml += '</tr>'
    }
    $("#tBodyId").html(tBodyHtml)
})
}

function disableBook(row) {
    console.log(row)
    let disableMethod = {
        url: ip + "book/disable/" + row.id,
        method: "PUT"
    }
    axios(disableMethod).then(data => {
        if(data.data.code == 0 || data.data.code == "0"){
        alertLayer("下架成功", data.data.msg, closeForm)
    }
else{
        alertLayer("下架失败", data.data.msg, closeForm)
    }
})
}

function enableBook(row) {
    let enableMethod = {
        url: ip + "book/enable/" + row.id,
        method: "PUT"
    }
    axios(enableMethod).then(data => {
        if(data.data.code == 0 || data.data.code == "0"){
        alertLayer("上架成功", data.data.msg, closeForm)
    }else{
        alertLayer("上架失败", data.data.msg, closeForm)
    }
})
}

function initUserTable(params) {
    let getUser = {
        url: ip + "user",
        method: "GET",
        params: params
    }
    axios(getUser).then(data => {
        initPage("userPage", getUserPageParam, data.data.data.total, params.page,params.pageSize);
    let usrtJson = data.data.data.records;
    //有数据才将表格显示出来
    if (usrtJson) {
        drawUser(usrtJson);
    }
})
}

function drawUser(data) {
    let userJson = data;
    if (userJson) {
        var keys = Object.keys(userJson[0]);
    }
    let userTitle = [
        {name: 'id', key: 'id'},
        {name: '用户名', key: 'name'},
        {name: '密码', key: 'password'},
        {name: '账号', key: 'account'},
        {name: '图片路径', key: 'icon'},
        {name: '图片', key: 'iconImg'},
        {name: "身份", key: 'identity'}
    ]
    let thHtml = "";
    let tBodyHtml = "";
    for (let title of userTitle) {
        if (title.key == "icon") {

        } else {
            thHtml += '<th>' + title.name + '</th>'
        }
    }
    thHtml += '<th style="width:280px">操作</th>'
    $("#uThId").html(thHtml);
    for (let item of userJson) {
        tBodyHtml += '<tr>'
        for (let title of userTitle) {
            let key = title.key;
            if (key == "icon") {
                tBodyHtml += '<td style="display: none">' + item[key] + '</td>'
            } else if (key == "iconImg") {
                tBodyHtml += '<td><img src="' + item.icon + '" class="table-img"></td>'
            } else {
                tBodyHtml += '<td>' + item[key] + '</td>'
            }
        }
        var $row = JSON.stringify(item).replace(/\"/g, "'");
        tBodyHtml += '<td><button type="button" class="layui-btn layui-btn-normal float-left mb-1" onclick="updateUser(' + $row + ')" >修改</button>'
        tBodyHtml += '<button type="button" class="layui-btn layui-btn-danger  mb-1" onclick="deleteUser(' + $row + ')">刪除</button></td>'
        tBodyHtml += '</tr>'
    }
    $("#uTBodyId").html(tBodyHtml)
}

function updateUser(row) {
    let html = pjUser();
    alertcomfirm2("updateUserLayer", "修改用户", html, "updateUserVerify", null, validateAddUser, ajaxUpdateUser, row, getUserData)
}

function ajaxUpdateUser(form) {
    let data = new FormData();
    for (let key of Object.keys(form)) {
        //避免新增时id插入
        if (form[key] != "undefined" && form[key] != undefined && key !== "file") {
            data.append(key, form[key])
        }
        if (key == "file") {
            data.append("file", fileImage)
        }

    }
    let updateUser = {
        url: ip + "user/",
        method: "put",
        data: data
    }
    axios(updateUser).then(() => {
        alertLayer("编辑用户", "修改成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("编辑用户", err
)
})
}

function deleteUser(rowList) {
    quesForm("你確定要刪除嗎？", deleteUserAjax, rowList)
}

function deleteUserAjax(form) {
    axios.delete(ip + "user/" + form.id).then(() => {
        alertLayer("删除用户", "删除成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("删除用户", err
)
})
}

function uploadBook() {
    let rowList = {
        name: "",
        author: "",
        publishingHouse: "",
        synopsis: "",
        // numberWorders:"",
        cover: "",
        status: 1
    }
    let html = pjBook();
    alertcomfirm2("addBookLayer", "添加书籍", html, "addBookVerify", '#publicationTime', validateAddBook, ajaxAddBook, rowList, getFormData)
}

//新增章节
function uploadChapter(row) {
    let html = pjChapter(row);
    let rowList = {
        id: "",
        bookId: row.id,
        bookName: row.name,
        // numberWorders:"",
        title: "",
        sort: "",
        content: ""
    }
    alertcomfirm5("uploadChapterLayer", "新增章节", html, "uploadChapterVerify", null, validateUploadChapter, ajaxUploadChapter, rowList, getChapterData)
}

//显示章节
 var chapters;

 function showChapters(rowList) {
     let chapterMethod = {
         url: ip + "chapter/" + rowList.id,
         method: "GET"
     }
     axios(chapterMethod).then(data => {
         console.log(data.data.data.records);
     chapters = data.data.data.records
     let html = pjChpaterList(chapters, rowList)
     alertcomfirm5("showChapterLayer", "章节", html, "ChapterVerify", null, validateNull, ajaxUploadChapter, rowList, getChapterList)

 })

 }

function getChapterList() {

}

function pjChpaterList(rowList, bookList) {
    let chapterTitle = [
        {name: "id", key: "id"},
        {name: "bookId", key: "bookId"},
        {name: "书名", key: "bookName"},
        {name: "内容", key: "content"},
        {name: "排序", key: "sort"},
        {name: "标题", key: "title"},
    ]
    let addRow = {
        id: '',
        bookId: bookList.id,
        bookName: bookList.name,
        title: '',
        sort: '',
        content: ''
    }
    let $addRowJson = JSON.stringify(addRow).replace(/\"/g, "'");
    var nhtml = '<div><button class="layui-btn " onclick="updateChapter(' + $addRowJson + ')">新增章节</button></div>'
    nhtml += '<table>'
    for (let i = 0; i < chapterTitle.length; i++) {
        let item = chapterTitle[i]
        if (item.key == "content") {

        } else {
            nhtml += '<th>' + item.name + '</th>'
        }
    }
    nhtml += '<th>操作</th>'
    for(let i=0;i<rowList.length;i++){
        nhtml+='<tr>'
        var item = rowList[i]
        var $row = JSON.stringify(item).replace(/\"/g,"'");
        for(let title of chapterTitle){
            let key = title.key
            let item2 = item[key]
            if(key=="content"){
                nhtml+='<td style="display: none" class="center">'+item2+'</td>'
            }else{
                nhtml+='<td class="center">'+item2+'</td>'
            }

        }
        nhtml+='<td><button class="layui-btn " onclick="updateChapter('+$row+')">修改</button></td>'
        nhtml+='<td><button class="layui-btn layui-btn-danger" onclick="deleteChapter('+$row+')">删除</button></td>'
        nhtml+='</tr>'
    }
    nhtml += '</table>'
    return nhtml;
}

function validateNull() {
}
function alertcomfirm5(id,title,html,layFilter,laydateId,validateMethod,ajaxMethod,data,renderForm){
    layui.use(['form','layer','laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        //數據驗證
        validateMethod(form)
        layer.open({
            //type：1  這樣才不會在數據驗證失敗後關閉彈出層
            // type:1,
            area: '450px',
            title:title,
            id:id,
            content: html  //這裡用html的拼接  nHtml
            //點擊右上角X
            ,cancel:function(){
                returnParentListen();
            }
            //點擊登錄
            ,success: function(layero, index){
                layero.addClass('layui-form');
                layero.find('.layui-layer-btn0').attr({
                    'lay-filter': layFilter,
                    'lay-submit': ''
                });
                layero.find('.layui-layer-btn .layui-layer-btn0').addClass('layui-hide');
                //重新渲染form
                // returnParentFormData(data);
                //渲染数据（修改）
                renderForm(data)
                form.render();
                if(laydateId!=null&&laydateId!=""){
                    laydate.render({
                        elem: laydateId,
                        type: 'datetime',
                        trigger:'click',
                        format:'yyyy-MM-dd HH:mm:ss',
                        value:data.publicationTime
                        // ,range: true //或 range: '~' 来自定义分割字符
                    });
                }
                // parentListen();
            },

            //點擊確定
            yes: function(index, layero){
                form.on('submit('+layFilter+')', function (data) {
                    //TODO   ajax
                    ajaxMethod(data.field)
                    console.log("yes")
                    return false
                    // layer.closeAll()
                })

            },
        })
    })
}

//修改章节
function updateChapter(row) {
    let rowList;

    rowList = {
        id: row.id || '',
        bookId: row.bookId || '',
        bookName: row.bookName || '',
        title: row.title || '',
        sort: row.sort || '',
        content: row.content || ''
    }
    let html = pjUpdateChapter(rowList);
    if (rowList.id == '' || rowList.id == undefined) {
        //新增
        alertcomfirm2("uploadChapterLayer", "新增章节", html, "uploadChapterVerify", null, validateUploadChapter, ajaxUploadChapter, rowList, getChapterData)
    } else {
        alertcomfirm2("uploadChapterLayer", "修改章节", html, "uploadChapterVerify", null, validateUploadChapter, ajaxUpdateChapter, rowList, getChapterData)
    }
}

function updateBook(rowList) {
    let html = pjBook();
    alertcomfirm2("addBookLayer", "修改书籍", html, "updateBookVerify", '#publicationTime', validateAddBook, ajaxUpdateBook, rowList, getFormData)
}

function getUserData(rowList) {
    $("#file").change(function (event) {
        var input = event.target;
        fileImage = event.target.files[0];
        $("#img").attr("src", URL.createObjectURL(fileImage))
    })
    document.getElementById("id").value = rowList.id;
    document.getElementById("name").value = rowList.name;
    document.getElementById("password").value = rowList.password;
    document.getElementById("account").value = rowList.account;
    if (rowList.icon) {
        document.getElementById('img').src = rowList.icon;
    }
    document.getElementById("identity").value = rowList.identity;

}

function getBookTypeData(rowList) {
    document.getElementById("bookId").value = rowList.bookId;
    document.getElementById("typeId").value = rowList.typeId;
}

function getTypeData(rowList) {
    document.getElementById("id").value = rowList.id;
    document.getElementById("typeName").value = rowList.typeName;
}

//渲染後設置值獲取數據(修改的數據打開在這裡賦值)
function getChapterData(rowList) {
    console.log("test111111111111111", rowList)
    document.getElementById("id").value = rowList.id;
    document.getElementById("bookId").value = rowList.bookId;
    document.getElementById("bookName").value = rowList.bookName;
    // document.getElementById("numberWorders").value=rowList.content.split("").length;
    document.getElementById("title").value = rowList.title;
    document.getElementById("sort").value = rowList.sort;

    document.getElementById("content").value = rowList.content;
    openEdit();
    console.log("editor2", editor2)
    // $("#content").html(rowList.content)

}

function getChapterDelete() {

}

var fileImage;

function getFormData(rowList) {
    $("#file").change(function (event) {
        var input = event.target;
        fileImage = event.target.files[0];
        $("#img").attr("src", URL.createObjectURL(fileImage))
    })
    document.getElementById("id").value = rowList.id;
    document.getElementById("name").value = rowList.name;
    document.getElementById("author").value = rowList.author;
    document.getElementById("status").value = rowList.status;
    document.getElementById("publishingHouse").value = rowList.publishingHouse;
    document.getElementById("synopsis").value = rowList.synopsis;
    if (rowList.cover) {
        document.getElementById('img').src = rowList.cover;
    }

    document.getElementById("publicationTime").value = rowList.publicationTime;
    // clickImage()
}

function deleteBook(rowList) {
    quesForm("你確定要刪除吗？", deleteBookAjax, rowList)
}

function deleteChapter(data) {
    let deleteChapterParam = {
        url: ip + "chapter/" + data.id,
        method: "delete"
    }
    axios(deleteChapterParam).then(() => {
        alertLayer("删除章节", "删除成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("删除章节", err
)
})
}

var allBookTypes = [];

function getAllBookTypes() {
    //获取所有type
    let getType = {
        url: ip + "type",
        method: "GET",
    }
    axios(getType).then(data => {
        allBookTypes = data.data.data.records;
})
}

function typeBook(row) {
    var bookTypes = []
    //获取该书籍的所有分类
    let typeByBook = {
        url: ip + "book/" + row.id + "/types",
        method: "GET"
    }
    axios(typeByBook).then(data => {
        if(data.data.code == 0 || data.data.code == "0"
)
    {
        bookTypes = data.data.data
        console.log(bookTypes)
        var nHtml = '<div class="layui-form-item" style="display: none">'
        nHtml += '<label class="layui-form-label">id</label>'
        nHtml += '<div class="layui-input-inline">'
        nHtml += ' <input type="text" name="bookId" id="bookId" autocomplete="off" class="layui-input">'
        nHtml += ' </div>'
        nHtml += '</div>'

        nHtml += '<div class="layui-form-item">'
        nHtml += '<label class="layui-form-label">已有类别</label>'
        nHtml += '<div class="layui-input-inline">'
        for (let i = 0; i < bookTypes.length; i++) {
            let item = bookTypes[i]
            var $row = JSON.stringify(item).replace(/\"/g, "'");
            nHtml += ' <span class="mr-1 cursor" onclick="deleteBookType(' + $row + ')">' + item.type.typeName + '</span>'
        }
        nHtml += ' </div>'
        nHtml += '</div>'

        nHtml += '<label class="layui-form-label">书籍类别</label>'
        nHtml += '<div class="layui-input-block">'
        nHtml += '<select name="typeId" id="typeId" lay-verify="typeId">'
        nHtml += '<option value="">---请选择---</option>'
        for (let item of allBookTypes) {
            nHtml += '<option value=' + item.id + '>' + item.typeName + '</option>'
        }
        nHtml += '</select>'

        nHtml += '</div>'
        let rowList = {
            bookId: row.id,
            typeId: ""
        }
        var area = ['500px', '400px']
        alertcomfirmSelf("addBookTypeLayer", "添加书籍类型", nHtml, "addBookTypeVerify", null, validateBookType, ajaxAddBookType, rowList, getBookTypeData, area)

    }
})


}

function deleteBookType(row) {
    quesForm("你確定要刪除" + row.type.typeName + "嗎？", deleteBookTypeAjax, row)
}

function deleteBookTypeAjax(row) {
    let deleteMethod = {
        url: ip + "book-type/" + row.id,
        method: "DELETE"
    }
    axios(deleteMethod).then(data => {
        alertLayer("删除", data.data.msg, closeForm
)
    ;
})
}

function validateUploadChapter() {

}

function validateChapterDelete() {

}

function validateAddBook(form) {
    var form = layui.form;
    form.verify({
        lusername: function (value) {
            if (value.length == 0) {
                return '用戶名不能爲空'
            }
        },
        lpassword: function (value) {
            if (value.length == 0) {
                return '密碼不能爲空'
            }
        }
    });
}

function ajaxAddBook(form) {
    let data = new FormData();
    for (let key of Object.keys(form)) {
        //避免新增时id插入
        if (form[key] != "undefined" && form[key] != undefined && key !== "file") {
            data.append(key, form[key])
        }
        if (key == "file") {
            data.append("file", fileImage)
        }

    }
    // data.append("image",form['file'])
    let addBook = {
        url: ip + "book",
        method: "POST",
        data: data
    }
    axios(addBook).then(res => {
        alertLayer("编辑书籍", "新增成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("编辑书籍", err
)
    ;
})
}

function ajaxDeleteChapter(data) {
    let deleteChapterParam = {
        url: ip + "chapter/" + data.id,
        method: "delete"
    }
    axios(deleteChapterParam).then(() => {
        alertLayer("删除章节", "删除成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("删除章节", err
)
})
}

function ajaxUploadChapter(data) {
    data.content = editor2.txt.html()
    let addChapter = {
        url: ip + "chapter",
        method: "POST",
        data: data
    }
    axios(addChapter).then(() => {
        alertLayer("新增章节", "新增成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("新增章节", err
)
})
}

function ajaxUpdateChapter(data) {
    console.log(editor2.txt.html())
    data.content = editor2.txt.html()
    // data.content = document.getElementById("content");
    let addChapter = {
        url: ip + "chapter",
        method: "PUT",
        data: data
    }
    axios(addChapter).then(() => {
        alertLayer("修改章节", "修改成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("修改章节", err
)
})
}

function addUser() {
    let rowList = {
        name: "",
        password: "",
        account: "",
        icon: "",
        identity: ""
    }
    let html = pjUser();
    alertcomfirm2("addUserLayer", "添加用户", html, "addUserVerify", null, validateAddUser, ajaxAddUser, rowList, getUserData)
}

function addType() {
    let rowList = {
        id: "",
        typeName: ""
    }
    let html = pjType();
    alertcomfirm2("addTypeLayer", "添加类型", html, "addTypeVerify", null, validateAddType, ajaxAddType, rowList, getTypeData)
}

function updateType(row) {
    let html = pjType();
    alertcomfirm2("updateTypeLayer", "修改类型", html, "updateUserVerify", null, validateAddUser, ajaxUpdateType, row, getTypeData)

}

function deleteType(row) {
    quesForm("你確定要刪除嗎？", deleteTypeAjax, row)
}

function deleteTypeAjax(form) {
    axios.delete(ip + "type/" + form.id).then(() => {
        alertLayer("删除类型", "删除成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("删除类型", err
)
})
}

function validateAddUser(form) {
    var form = layui.form;
    form.verify({
        identity: function (value) {
            if (typeof value !== "number") {
                return "身份必须是数字"
            }
        }
    });
}

function validateBookType(form) {
    var form = layui.form;
    form.verify({
        typeId: function (value) {
            if (!value) {
                return "参数不能为空"
            }
        }
    });
}

function validateAddType(form) {
    var form = layui.form;
    form.verify({
        typeName: function (value) {
            if (!value) {
                return "参数不能为空"
            }
        }
    });
}

function ajaxAddBookType(form) {
    // validateBookType(form)
    if (form.typeId && form.bookId) {
        let addBookType = {
            url: ip + "book-type/",
            method: "POST",
            data: form
        }
        axios(addBookType).then(() => {
            alertLayer("编辑书籍类型", "编辑成功", closeForm
    )
        ;
    }).
        catch(err => {alertLayer2("编辑用户", err
    )
    })
    }
}

function ajaxAddUser(form) {
    let data = new FormData();
    for (let key of Object.keys(form)) {
        //避免新增时id插入
        if (form[key] != "undefined" && form[key] != undefined && key !== "file") {
            data.append(key, form[key])
        }
        if (key == "file") {
            data.append("file", fileImage)
        }

    }
    let addUser = {
        url: ip + "user/register",
        method: "POST",
        data: data
    }
    axios(addUser).then(() => {
        alertLayer("编辑用户", "新增成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("编辑用户", err
)
})
}

function ajaxAddType(form) {
    let addUser = {
        url: ip + "type",
        method: "POST",
        data: form
    }
    axios(addUser).then(() => {
        alertLayer("新增类型", "新增成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("新增类型", err
)
})
}

function ajaxUpdateType(form) {
    let addUser = {
        url: ip + "type",
        method: "PUT",
        data: form
    }
    axios(addUser).then(() => {
        alertLayer("修改类型", "修改成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("修改类型", err
)
})
}

function closeForm() {
    layer.closeAll();
    init();
}

function deleteBookAjax(form) {
    axios.delete(ip + "book/" + form.id).then(() => {
        alertLayer("删除书籍", "删除成功", closeForm
)
    ;
}).
    catch(err => {alertLayer2("删除书籍", err
)
})
}

var getFile;

function ajaxUpdateBook(form) {
    let url = document.getElementById('img').src;
    if (url) {
        var img = url;
        var image = new Image();
        image.src = img;
        image.onload = function () {
            //这样就获取到了文件的Base64字符串
            var base64 = getBase64Image(image);
            //Base64字符串转二进制
            getFile = dataURLtoBlob(base64);
        }
    }
    if (!fileImage) {
        fileImage = getFile
    }
    let data = new FormData();
    for (let key of Object.keys(form)) {
        //避免新增时id插入
        if (form[key] != "undefined" && form[key] != undefined && key !== "file") {
            data.append(key, form[key])
        }
        if (key == "file") {
            data.append("file", fileImage)
        }
    }
    let updateBook = {
        url: ip + "book/",
        method: "PUT",
        data: data
    }
    axios(updateBook).then(() => {
        alertLayer("修改书籍", "修改成功", closeForm);
}).catch(err => alertLayer2("修改书籍", err)
)
}

function initTab() {

}

function returnZindex() {
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}

function changeZindex() {
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}

var openFile = function (event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function () {
        if (reader.result) {
            //显示文件内容
            $("#output").html(reader.result);
        }
    };
    reader.readAsText(input.files[0]);
};

function mouseup() {
    let w = window.getSelection();
}