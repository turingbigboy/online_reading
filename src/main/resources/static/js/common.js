function alertLayer(title,content,callback){
    layer.open({
        title: title
        ,content: content
        ,btn: ['确定']
        ,yes: function(index, layero){
            callback()
        },
        cancel: function(index, layero){
        callback()
    },
    });

}

//无关闭
function alertLayer2(title,content){
    layer.open({
        title: title
        ,content: content

    });

}



function alertForm(title,id,callback){
        layer.open({
            title:title,
            content: $(id).html()
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
                callback()
            },
            
        })
    }
    function quesForm(content,callback,data){
      layer.confirm(content, {
        offset: '200px',
        btn: ['確定','取消'] //按钮
        ,cancel: function(index, layero){
        returnParentListen();
        console.log("cancel")
        }
      }, function(){
        //yes
        callback(data)
      }, function(){
        returnParentListen();
        console.log("no")
      });
      }

    //彈出窗口模板
    function alertcomfirm(id,title,contentId,layFilter,validateMethod,ajaxMethod){
      layui.use(['form','layer'], function(){
        var form = layui.form;
        //數據驗證  
        validateMethod(form)
        layer.open({
        //type：1  這樣才不會在數據驗證失敗後關閉彈出層
        type:1,
        offset: '200px',
        area: '350px',
        title:title,
        id:id,
        content: $(contentId).html()
        ,btn: ['确定']
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
        //重新渲染form
        form.render();

          parentListen();
        },
        
        //點擊確定
        yes: function(index, layero){
        form.on('submit('+layFilter+')', function (data) {
        // layer.alert(JSON.stringify(data.field))
        //TODO   ajax 
        ajaxMethod(data.field)
        console.log("yes")
        // returnParentListen();
        // console.log(data)
        return false
        // layer.closeAll()
        })
        
        },
      })
    })
  }


     //彈出窗口模板2  带时间laydate的弹窗  html位js拼接的数据
     function alertcomfirm2(id,title,html,layFilter,laydateId,validateMethod,ajaxMethod,data,renderForm){
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
        ,btn: ['确定','取消']
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



//彈出窗口模板2  带时间laydate的弹窗  html位js拼接的数据
function alertcomfirmSelf(id,title,html,layFilter,laydateId,validateMethod,ajaxMethod,data,renderForm,area){
    layui.use(['form','layer','laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        //數據驗證
        validateMethod(form)
        layer.open({
            //type：1  這樣才不會在數據驗證失敗後關閉彈出層
            // type:1,
            area: area,
            title:title,
            id:id,
            content: html  //這裡用html的拼接  nHtml
            ,btn: ['确定']
            //點擊右上角X
            ,cancel:function(){
                returnParentListen();
            }
            //點擊登錄
            ,success: function(layero, index){
                parentListen();
                layero.addClass('layui-form');
                layero.find('.layui-layer-btn0').attr({
                    'lay-filter': layFilter,
                    'lay-submit': ''
                });
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

//彈出窗口模板3  带时间laydate和带图片的弹窗  html位js拼接的数据
function alertcomfirm3(id,title,html,layFilter,laydateId,validateMethod,ajaxMethod,data,renderForm,imageId){
    layui.use(['form','layer','laydate','upload'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.jquery
            ,upload = layui.upload;
        //數據驗證
        validateMethod(form)
        layer.open({
            //type：1  這樣才不會在數據驗證失敗後關閉彈出層
            // type:1,
            area: '450px',
            title:title,
            id:id,
            content: html  //這裡用html的拼接  nHtml
            ,btn: ['确定','取消']
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
                upload.render({
                    elem: imageId
                    // ,url: 'https://httpbin.org/post' //改成您自己的上传接口
                    ,auto: false
                    // //,multiple: true
                    // ,done: function(res){
                    //     layer.msg('上传成功');
                    //     console.log(res)
                    // }
                });
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



//彈出窗口模板4  带时间laydate的弹窗  html位js拼接的数据
function alertcomfirm4(id,title,html,layFilter,laydateId,validateMethod,ajaxMethod,data,renderForm){
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
            ,btn: ['确定']
            //點擊右上角X
            ,cancel:function(){
                returnParentListen();
            }
            //點擊登錄
            ,success: function(layero, index){
                parentListen();
                layero.addClass('layui-form');
                layero.find('.layui-layer-btn0').attr({
                    'lay-filter': layFilter,
                    'lay-submit': ''
                });
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
    function parentListen(){
      parent.changeZindex();
    }
    function returnParentListen(){
      parent.returnZindex();
    }

    //自适应高度
    function reInitFrameHeight(frameId,mainId) {
        let screenHeight = window.screen.height;
        var scrollHeight=document.getElementById(mainId).scrollHeight;
        var scrollHeight2=document.getElementById(frameId).scrollHeight;
        var dom=$("#frameContent")[0]
        dom.height = scrollHeight+"px";
    }



    function initIP(){
        const ip = "http://localhost:8080/online_reading/"
        return ip;
    }

    function initPage(id,getPageParam,total,currentPage,limit) {
        layui.use('laypage', function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: id //注意，这里的 test1 是 ID，不用加 # 号
                ,count: total //数据总数，从服务端得到
                ,curr:currentPage
                ,limit: limit
                ,jump: function(obj, first){
                    //obj包含了当前分页的所有参数，比如：
                    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                    console.log(obj.limit); //得到每页显示的条数
                    //首次不执行
                    if(!first){
                        console.log("分页执行")
                        //do something
                        getPageParam(obj.curr,obj.limit)
                    }

                }
            });
        });
    }
    function layTable(id,cols,data) {
        layui.use('table', function(){
            var table = layui.table;
            //展示已知数据
            table.render({
                elem: id
                ,cols: cols
                ,data: data
                //,skin: 'line' //表格风格
                ,even: true
                //,page: true //是否显示分页
                //,limits: [5, 7, 10]
                //,limit: 5 //每页默认显示的数量
            });
        });
    }

//axios封装post请求
function axiosPostRequst(url,data) {
  let result = axios({
      method: 'post',
      url: "http://localhost:8080/online_reading/"+url,
      data: data,
      transformRequest:[function(data){
          let ret = '';
          for(let i in data){
              ret += encodeURIComponent(i)+'='+encodeURIComponent(data[i])+"&";
          }
          return ret;
      }],
      header:{
          'Content-Type':'application/x-www-form-urlencoded'
      }
  }).then(resp=> {
      return resp.data;
  }).catch(error=>{
      return "exception="+error;
  });
  return result;
}


//axios封装get请求
function axiosGetRequst(url,data) {
  let result = axios({
      method: 'get',
      url: "http://localhost:8080/online_reading/"+url,
      data: data,
      transformRequest:[function(data){
          let ret = '';
          for(let i in data){
              ret += encodeURIComponent(i)+'='+encodeURIComponent(data[i])+"&";
          }
          return ret;
      }],
      header:{
          'Content-Type':'application/x-www-form-urlencoded'
      }
  }).then(resp=> {
      return resp.data;
  }).catch(error=>{
      return "exception="+error;
  });
  return result;
}

/**
 * 网络图像文件转Base64
 */
function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
    var dataURL = canvas.toDataURL("image/" + ext);
    return dataURL;
}


/**
 *Base64字符串转二进制
 */
function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {
        type: mime
    });
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
