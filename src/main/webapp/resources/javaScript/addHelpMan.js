/**
 * Created by aihaitao on 16/5/2017.
 */

window.onload = function () {

    //设置input的初始值
};

function checkCityName() {
    let cityName=document.getElementById("cityName").value;
    //console.log(cityName);
    if (cityName==null || cityName==""){
         return alert("城市名字不能为空")
    }else {
        if (cityName.length>8){
            return alert("城市名字太长")
        }
    }
}

function checkShouZhurenName() {
    let shouZhurenName=document.getElementById("shouZhurenName").value;
    //console.log(cityName);
    if (shouZhurenName==null || shouZhurenName==""){
        return alert("名字不能为空")
    }else {
        if (shouZhurenName.length>8){
            return alert("名字太长")
        }
    }
}

function checkAcceptMoneyName() {
    let acceptMoneyName=document.getElementById("acceptMoneyName").value;
    //console.log(cityName);
    if (acceptMoneyName==null || acceptMoneyName==""){
         alert("名字不能为空")
    }else {
        if (acceptMoneyName.length>8){
            return alert("名字太长")
        }
    }
}



function  checkShouZhureniDentityNo() {

    let shouZhureniDentityNo=document.getElementById("shouZhureniDentityNo").value;
    //console.log(cityName);
    if (shouZhureniDentityNo==null || shouZhureniDentityNo==""){
        return alert("受助人身份证不能为空")
    }else {
        if (shouZhureniDentityNo.length!=18 ){
            alert("身份证长度不对")
        }
    }
}

function  checkAcceptMoneyPhone(){
    let acceptMoneyPhone=document.getElementById("acceptMoneyPhone").value;
    //console.log(cityName);
    if (acceptMoneyPhone==null || acceptMoneyPhone==""){
        return alert("收款人手机号不能为空")
    }else {
        if (acceptMoneyPhone.length!=11 ){
            console.log(acceptMoneyPhone.length);
            return alert("手机号码有误")
        }
    }

}

function  checkChengnuoContent(){
    let chengnuoContent=document.getElementById("chengnuoContent").value;
    //console.log(cityName);
    if (chengnuoContent==null || chengnuoContent==""){
        return alert("承诺不能为空，至少10字")
    }else {
        if (chengnuoContent.length<10 ){
            return alert("承诺太短")
        }
    }
}

function  checkendDate(){
    let endDate=document.getElementById("endDate").value;
    console.log(endDate)
}

function  checkTargetMoney(){

    function isInteger(obj) {
        return typeof obj === 'number' && obj%1 === 0
    }

    let targetMoney=document.getElementById("targetMoney").value;

    if (targetMoney==null || targetMoney==""){
        return alert("募捐金额不能为空")
    }else {

        let  targetMoneyInt=parseInt(targetMoney);
        //console.log(targetMoneyInt);
        if( !isInteger(targetMoneyInt)){
            return alert("必须为整数")
        }
    }
}
function  checkMoneyTitle(){
    let moneyTitle=document.getElementById("moneyTitle").value;
    //console.log(moneyTitle);
    if (moneyTitle==null || moneyTitle==""){
        return alert("筹款标题不能为空")
    }else {
        if(moneyTitle.length<5){
            return alert("标题太短")
        }
    }

}

function checkContent(){
    let content=document.getElementById("content").value;
    //console.log(moneyTitle);
    if (content==null || content==""){
        return alert("情况描述不能为空")
    }else {
        if(content.length<200){
            return alert("不能低于200字")
        }
    }
}





