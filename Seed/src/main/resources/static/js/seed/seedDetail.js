// console.log("상품 상세");


const minus = document.getElementById("minus"); // 마이너스 버튼
const plus = document.getElementById("plus");   // 플러스 버튼
const count = document.getElementById("count"); // 수량

const add = document.getElementById("add"); // 담기 버튼


// minus 버튼을 눌렀을때 수량 -1
minus.addEventListener("click", () => {
    if(parseInt(count.innerText) == 1){
        alert("1개 이상의 씨앗을 담아주세요");
        return;
    }

    count.innerText = parseInt(count.innerText) - 1;


});

// plus 버튼을 눌렀을때 수량 +1
plus.addEventListener("click", () => {
    
    count.innerText = parseInt(count.innerText) + 1;

});



// 장바구니에 추가하는 기능
add.addEventListener("click", () => {

    if(memberNo == null){
        alert("로그인 후 이용해주세요");
        location.href = "/member/login";
        return;
    }

    const obj = {
        "memberNo" : memberNo,
        "seedNo" : seedNo,
        "count" : parseInt(count.innerText)
    }
    
    fetch("/cart/seedAdd",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(obj)
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            alert("장바구니에 추가 했습니다!");
        }else{
            console.log("장바구니 추가 실패...");
        }
    
    })

});