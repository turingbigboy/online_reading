$(function() {
    init();
});
function init(){
    var E = window.wangEditor
    var editor2 = new E('#div3')
    editor2.customConfig.menus = [  //菜单配置，不要可以直接去掉这个默认全部功能都有如下图
        'head',
        'bold',
        'italic',
        'underline'
    ]
    editor2.create()
}