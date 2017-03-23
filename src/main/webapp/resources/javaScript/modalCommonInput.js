/**
 * Created by mfhj-dz-001-424 on 17/3/14.
 *
 * 回复，评论，更新等等
 */


var w = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
var h = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

function clickMe(){
    console.log("点击不到我的")
}

/*在弹出的时候,获取是回复还是更新等,对应更改弹出框相应样式和设置不同标记,为后续显示"回复""提交",等判断该调用的网络接口.
 *
 * 1.这要求,在回复评论,评论,进度更新,评论更新,举报,证实等地方,需要做不同的标记.
 * 2.点击时,依据1处的标记,设置弹出框的样式,和设置样式框的标记
 * 3.提交时,依据2处的样式框的标记,在vue中调用不同的网络请求
 *
 * */
function alertComment(){
    let  modal=document.getElementById("modal");
    let  tips=document.getElementById("tips");

    if (modal.style.display=="none" ||modal.style.display==""){
        modal.style.display="block";
        tips.style.display="block";
    }
}

function cancel(){

    let  modal=document.getElementById("modal");
    let  tips=document.getElementById("tips");

    if (modal.style.display=="" || modal.style.display=="block"){
        modal.style.display="none";
        tips.style.display="none";
    }
}

function reply(){
    /*1.先隐藏模态框*/
    let  modal=document.getElementById("modal");
    let  tips=document.getElementById("tips");

    if (modal.style.display==""){
        modal.style.display="none";
        tips.style.display="none";
    }
    /*2.发送网络请求*/

    /*3.根据网络请求的结果,显示一个提示,1秒后消失*/
    let  dialog=document.getElementById("dialog");
    let  dialogTips=document.getElementById("dialogTips");

    if (dialog.style.display=="" || dialog.style.display=="none"){
        dialog.style.display="none";
        dialogTips.style.display="none";
    }

}
