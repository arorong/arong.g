<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="login.css">
<title>선정 도서관</title>
<style>
@import
   url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap')
   ;

* {
   font-family: 'Noto Sans KR', sans-serif !important;
   box-sizing: border-box;
   font-weight: 500;
}
</style>
<!-- 중복체크 Ajax script -->
<script   src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.js"></script>
<script>
   $(function() {
      //아이디 중복체크
      $("#vid").click(function() {
         let ids = document.joinform.id.value;
         let idval = {
            "id" : ids
         };
         if (ids == '') {
            alert('아이디를 입력해주세요');
         } else {
            $.ajax({
               url : "loginCheck",
               type : "post",
               data : idval,
               dataType : "json",
               cache : false,
               async : false,
               success : function(data) {
                  console.log('아이디 중복체크 success');
                  if (data.map.str1 == '1')
                     cnt++;
                  alert(data.map.id + '는 ' + data.map.str);
               },
               error : function(err) {
                  console.log('error객체 : ', err);
               }
            });
         }
      });

      //전화번호 중복체크
      $("#vph").click(function() {
         let phs = document.joinform.ph.value;
         let phval = {
            "ph" : phs
         };
         console.log(ph);
         console.log(phval);
         if (phs == '') {
            alert('전화번호를 입력해주세요');
         } else {
            $.ajax({
               url : "phoneCheck",
               type : "post",
               data : phval,
               dataType : "json",
               cache : false,
               async : false, //
               success : function(data) {
                  if (data.map.str1 == '1')
                     cnt++; //
                  alert(data.map.ph + '는 ' + data.map.str);
               },//
               error : function(err) {
                  console.log('error객체 : ', err);
               }
            });
         }
      });

   });

   //아이디, 전화번호 중복확인 요청
//    let cnt = 0;
//    function enableSubmitButton() {
//       if (cnt >= 2) {
//          joinform.submit();
//       } else {
//          alert("아이디나 전화번호 중복체크를 해주세요.");
//       }
//    };
</script>
<style>
   .tooltip {
     position: relative;
   }
   .tooltip .tooltiptext {
     visibility: hidden;       /* 이벤트가 없으면 툴팁 영역을 숨김 */
     width: 300px;             /* 툴팁 영역의 넓이를 설정 */
     background-color: black;
     color: #fff;
     text-align: center;
     border-radius: 6px;
     padding: 5px 0;
     top:50px;
     position: absolute;       /* 절대 위치를 사용 */
     z-index: 1;
     left: 60px;
   }
   
   .tooltip:hover .tooltiptext {
     visibility: visible;      /* hover 이벤트 발생시 영역을 보여줌 */
   }
   
   button.menu_btn { /*비중요버튼*/
      background-color: white;
    color: black;
      border: 1px solid black;
}

button.menu_btn:hover{ 
   background-color: #E9E5E4;
   border: none; 
}


#menu_btn1{ /*중요버튼*/
   background-color:black; 
   color: white;
}

#menu_btn1:hover{
    background-color: #D7B997;
    color:black;
    border: none;
}

</style>
</head>
<body>
   <div class="wrapSign" id="wrap"><img src="./img/logo.png" onclick="javascript:location.href='Main.jsp'">
      <form class="joinform"  name="joinform" action="./memberJoinAction.me" method="post">
         <h2>회원가입 페이지</h2>
         <table class="section top">
            <tr>
               <td class="tooltip" colspan="3">
                  <input type="text" class="menu_h" id="id" name="id" placeholder="id" minlength="5" maxlength="20" pattern="[a-z0-9_\-]+" required />
                  <span class="tooltiptext tooltip-left">5~20자 사이, 영어 소문자, 숫자, 특수문자(_\-)까지 사용가능</span></td>
               </td>
               <td><input type="button" id="vid" value="아이디 중복체크">
               </button></td>
            </tr>
            <tr>
               <td class="tooltip" colspan="4">
               <input type="password" class="menu_h" name="pw" id="pw" placeholder="비밀번호" minlength="8" maxlength="16" pattern="[a-zA-Z0-9_\-~!@#$%^&*()]+" required />
               <span class="tooltiptext tooltip-left">8~16자 사이, 영어 대소문자, 숫자, 특수문자(_\-~!@#$%^&*())까지 사용가능</span></td>
<!--                   <input type="password" class="menu_h" name="pw" id="pw" placeholder="비밀번호"/> -->
               </td>
            </tr>
            <tr>
               <td colspan="4">
                  <!-- 8~16자 사이, 영어 대소문자, 숫자, 특수문자(_\-~!@#$%^&*())까지 사용가능 -->
                  <input type="password" class="menu_h" name="pw1" id="pw1" placeholder="비밀번호확인" minlength="8" maxlength="16" pattern="[a-zA-Z0-9_\-~!@#$%^&*()]+" required/>
               </td>
            </tr>
            <tr>
               <td colspan="4"><input type="text" class="menu_h" name="nm"
                  id="nm" placeholder="이름" minlength="1" required /></td>
            </tr>
            <tr>
               <td colspan="4"><input type="text" class="menu_h"
                  name="birth" id="birth" placeholder="생년월일 예) 19850101" minlength="8"
                  maxlength="8" pattern="[0-9]{8}" required /></td>
            </tr>
            <tr>
               <td colspan="3"><input type="tel" class="menu_h" id="ph"
                  name="ph" placeholder="전화번호 예) 01012341234" minlength="11"
                  maxlength="11" pattern="[0-9]{11}" required /></td>
               <td><input type="button" button id="vph" value="전화번호 중복체크">
               </button></td>
            </tr>
            <tr>
               <td colspan="4">
                  <!--                
               <input type="email" class="menu_h" id="email" name="email" placeholder="이메일" minlength="5" maxlength="40" pattern="[a-zA-Z0-9_\-~!@#$%^&*()]+[@]" required /></td> -->
                  <input type="email" class="menu_h" id="email" name="email" placeholder="이메일" />
               </td>
            </tr>
            <tr>
               <td rowspan="3">주소</td>
               <td colspan="2"><input type="text" class="menu_h"
                  id="postcode" name="postcode" placeholder="우편번호" readonly></td>
               <td><input type="button" class="menu_h"
                  onclick="execDaumPostcode()" value="우편번호 찾기"></td>
            </tr>
            <tr>
               <td colspan="3"><input type="text" class="menu_h"
                  id="address" name="addr" placeholder="주소" readonly></td>
            </tr>
            <tr>
               <td colspan="3"><input type="text" class="menu_h"
                  id="detailAddress" name="addr1" placeholder="상세주소">
               </td>

            </tr>
         </table>

      <div class="btnwrap">
         <button type="button" class="menu_btn" onclick="javascript:history.back()">뒤로가기</button>
         <button type="submit" id = "menu_btn1" class="menu_btn" onclick="pwchk()">가입하기</button>
         <button type="reset" class="menu_btn" onclick="javascript:joinform.reset()">다시작성</button>
      </div>
         </form>

      </div>

</body>
<!-- daum주소 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
      function execDaumPostcode() {
         new daum.Postcode(
               {
                  oncomplete : function(data) {
                     // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                     // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                     // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                     var addr = ''; // 주소 변수
                     var extraAddr = ''; // 참고항목 변수

                     //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                     if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                     } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                     }

                     // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                     if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== ''
                              && /[동|로|가]$/g.test(data.bname)) {
                           extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== ''
                              && data.apartment === 'Y') {
                           extraAddr += (extraAddr !== '' ? ', '
                                 + data.buildingName
                                 : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                           extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        //                 document.getElementById("extraAddress").value = extraAddr;

                     } else {
                        document.getElementById("extraAddress").value = '';
                     }

                     // 우편번호와 주소 정보를 해당 필드에 넣는다.
                     document.getElementById('postcode').value = data.zonecode;
                     document.getElementById("address").value = addr;
                     // 커서를 상세주소 필드로 이동한다.
                     document.getElementById("detailAddress").focus();
                  }
               }).open();
      }
      
      
      function pwchk(){
            var p1 = document.getElementById('pw').value;
            var p2 = document.getElementById('pw1').value;
            
            
            if(p1 == ""){
               alert('비밀번호를 입력해주세요.');
               document.getElementById('pw').focus();
               return false;
            } else if(p2 == ""){
               alert('비밀번호를 확인해주세요.');
               document.getElementById('pw1').focus();
               return false;
            } else if(p1 != p2){
               alert('비밀번호가 일치하지 않습니다. \n다시 확인해주세요.');
               return false;
            } else{
//                alert("정보가 업데이트 되었습니다.");

              if (cnt >= 2) {
                  joinform.submit();
               } else {
                  alert("아이디나 전화번호 중복체크를 해주세요.");
                  return false;
               }
//                joinform.submit();
            }
         }
   </script>
</html>