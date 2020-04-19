$(function() {
    init();
});
function init(){
    returnZindex();
    uploadImage();
    initForm();
}
function initForm(){
    layui.use('form', function(){
        //只有执行了这一步，部分表单元素才会自动修饰成功
        var form = layui.form;
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
        //监听提交
        // form.on('submit(formDemo)', function(data){
        //   layer.msg(JSON.stringify(data.field));
        //   return false;
        // });
    });
}
function uploadImage(){
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#demo1'
            ,url: 'https://httpbin.org/post' //自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}

var openFile = function(event) {
    var input = event.target;
    console.log(event)
    var reader = new FileReader();
    reader.onload = function() {
        if(reader.result) {
            //显示文件内容
            $("#output").html(reader.result);
        }
    };
    reader.readAsText(input.files[0]);
};
function mouseup(){
    // console.log("mouseup")
    let w = window.getSelection();
}