<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="java.sql.*"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="inform.vo.InformBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.css" />
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- jQuery 추가 -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.10.3/gsap.js"></script>
<script src="https://unpkg.com/swiper@8/swiper-bundle.js"></script>
<title>선정 도서관</title>
<style>

* {
   box-sizing: border-box;
   position: relative;
}

body {
   margin: 0px;
}

/* 리셋 CSS */
* {
   margin: 0;
   padding: 0;
   box-sizing: border-box;
}

ul, li {
   list-style: none;
}

/* 슬라이드 Style */
.slidebox {
   max-width: 450px;
}

.slidebox .slidelist {
   white-space: nowrap;
   font-size: 0;
   overflow: hidden;
   width: 100%;
}

.slidebox .slidelist .slideitem {
   position: relative;
   display: inline-block;
   vertical-align: middle;
   width: 100%;
   transition: all 0.5s;
}

.slidebox .slidelist .slideitem>a {
   display: block;
   width: 760px;
   position: relative;
}

.slidebox .slidelist .slideitem>a img {
   z-index: -1;
   max-width: 120%;
}

.slidebox .slidelist .slideitem>a label {
   display: inline-block;
   position: absolute;
   text-align: center;
   top: 90%;
   transform: translateY(-70%);
   padding: 20px;
   border-radius: 50%;
   cursor: pointer;
   z-index: 9999;
   font-weight: bolder;
}

.slidebox .slidelist .slideitem>a label.prev {
   left: 357px;
   bottom: 10px;
   background: rgba(255, 255, 255, 0.8) url('./img/prev.png') center center/50%
      no-repeat;
}

.slidebox .slidelist .slideitem>a label.next {
   right: 315px;
   bottom: 10px;
   background: rgba(255, 255, 255, 0.8) url('./img/next.png') center center/50%
      no-repeat;
}

[name="slide"] {
   display: none;
}

#slide01:checked ~.slidelist .slideitem {
   transform: translateX(0);
   animation: slide01 5s infinite;
}

#slide02:checked ~.slidelist .slideitem {
   transform: translateX(-100%);
   animation: slide02 5s infinite;
}

#slide03:checked ~.slidelist .slideitem {
   transform: translateX(-200%);
   animation: slide03 5s infinite;
}

a {
   text-decoration: none;
}

/* --------------------------------------- */
.search {
   width: 100%;
}

.search>:nth-child(1) {
   margin: 0 auto;
}

.search section {
   clear: both;
   display: flex;
   justify-content: center;
}

.search section div {
   position: relative;
   overflow: hidden;
}

.search section img {
   width: 200px;
   margin: 0 10px;
   transform: scale(1, 1);
   opacity: 0.9;
}

.search section img:hover {	
   transform: scale(1.5);
}

.letter {
   position: absolute;
   top: 0%;
   width: 200px;
   margin: 0 10px;
   height: 100%;
   cursor: pointer;
   transition: 0.3s;
   opacity: 0;
   color: white;
   text-align: center;
   padding: 150px 0;
}

.letter:hover {
   background: rgba(0, 0, 0, 0.5);
   opacity: 1;
}

.search>:nth-child(2) {
   clear: both;
   width: 1000px;
   margin: 0 auto;
   padding: 20px 90px;
}

.search button:hover {
   opacity: 0.8;
}

.back {
   background-color: transparent !important;
}

/* ---------------- */
.info-main {
   position: relative;
   width: 93%;
   height: 312px;
   background-color: #fff;
   opacity: 0.9;
}

.the1 {
   display: flex;
   justify-content: space-between;
}

.back1 {
   height: 100%;
}

hr {
   color: #fff;
   margin-bottom: 25px;
}

.hr2 {
   marging-bottom: 10px;
}

@media ( min-width : 768px) {
   .imge {
      width: 100%;
      margin: 0 auto;
      display: flex;
      justify-content: center;
      flex-direction: column-reverse;
      align-items: center;
   }
}
</style>

</head>

<body>
   <jsp:include page="/header.jsp"></jsp:include>

   <style>
.table1, .table1 td {
   border: 4px solid #000;
   border-collapse: collapse;
}

.search img {
   width: 100px;
   height: 300px;
}

.h2in {
   padding-top: 15px;
   margin: 10px 50px;
   color: white;
}

.main_rightcontainer {
   margin: 50px auto;
}

.sub_container {
   position: relative;
   background-color: rgba(0, 0, 0, 0.4);
   width: 960px;
   max-width: 100%;
   height: 390px;
}

.textbar {
   margin-bottom: 65px;
   line-height: 12px;
   color: #fff;
   text-align: center;
   font-size: 1.2em;
}

.info {
   position: relative;
   width: 600px;
   height: 320px;
}

@media screen and (max-width: 768px) {
   .main_container {
      width: 100%;
      margin: 0 auto;
      display: flex;
      flex-direction: column-reverse;
      align-items: center;
   }
   div.imge:nth-last-of-type(1), div.imge:nth-last-of-type(2) {
      display: none;
   }
   .info-main {
      position: relative;
      width: 100%;	
      height: 312px;
      background-color: #fff;
      opacity: 0.9;
   }
   .slidebox {
      margin-top: 10px;
      max-width: 700px;
   }
   .slidebox .slidelist .slideitem>a label.prev {
      left: 520px;
      bottom: 10px;
      background: rgba(255, 255, 255, 0.8) url(./img/prev.png) center center/50%
         no-repeat;
   }
   .slidebox .slidelist .slideitem>a label.next {
      right: 151px;
      bottom: 10px;
      background: rgba(255, 255, 255, 0.8) url(./img/next.png) center center/50%
         no-repeat;
   }
   .info {
      position: relative;
      width: 700px;
      height: 320px;
   }
   
   .textbar .p1 {
      font-size: 1.2em!important;
   }
}


</style>
   <div class="main_rightcontainer" style="">
      <div class="textbar">
         <h2 style="margin-bottom: 30px;">선정도서관</h2>
         <p class="p1" stlye="font-size:">휴식과 만남, 그리고 책을 주제로 소통하는 문화 감성 공간</p>
         <br>
         <p class="p1">누구나 이용할 수 있도록 모두에게 열려 있습니다.
      </div>
      <table class="table1" style="width: 100%; height: 100%; text-align: center;">
         <tr>
            <article class="search">
               <div class="sub_container">
                  <h2 class="h2in">인기도서</h2>
                  <hr>
                  <section>
                     <div class="imge">
                        <a><img src="./img/무엇이나를행복하게만드는가.jpg" alt="무엇이나를행복하게만드는가"></a>
                        <a class="letter" href="searchbook.bok?word=무엇이 나를 행복하게 만드는가">무엇이 나를 행복하게 만드는가</a>
                     </div>

                     <div class="imge">
                        <a href="#"> <img src="./img/미움받을용기.jpg" alt="미움 받을 용기"></a>
                        <a class="letter" href="searchbook.bok?word=미움 받을 용기">미움 받을 용기</a>

                     </div>
                     <div class="imge">
                        <a href="#"><img src="./img/파타.jpg" alt="파타"></a>
                        <a class="letter" href="searchbook.bok?word=파타">파타</a>
                     </div>
                     <div class="imge">
                        <a href="#"><img src="./img/핵개인의시대.jpg" alt="핵개인의 시대"></a>
                        <a class="letter" href="searchbook.bok?word=핵개인의 시대">핵개인의 시대</a>
                     </div>

                  </section>
               </div>
            </article>
         </tr>
      </table>
   </div>

   <!-- 공지사항 게시판ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
   <style>
.noticeTable {
   position: relative;
   background-color: #fff;
   color: #000;
   width: 90%;
}

.rhdwl {
   pading: 10px
}

.news {
   padding: 15px;
}

.news>span {
   font-size: 20px;
   font-weight: bolder;
}

.the1>a>img {
   width: 20px;
}
</style>
   <script>
      $(document).ready(function() {
         $.ajax({
            url : 'informList1.bo',
            type : 'GET',
            success : function(data) {
               var tableContent = '';

               $('#noticeTable ').html(data);
            }
         });
      });
   </script>


   <div class="main_container"
      style="width: 100%; margin: 0 auto; display: flex; justify-content: center;">
      <div class="info">
         <div class="info-main">
            <div class="news">
               <span class="the1" style="padding: 10px 10px 0px 10px;">공지사항<a
                  href="informList.bo"><img src="./img/플러스.png"></a></span>

               <hr style="margin: 10px;">
               <table id="noticeTable" width="200px;">
                  <tr>
                     <th>제목</th>
                     <th>내용</th>
                  </tr>
               </table>
            </div>
         </div>
      </div>

      <!--    <script>
      $(function(){
         imgReSize();
         window.addEventListener("resize",function(){
            imgReSize();
         });
      });
      
      function imgReSize(){
         let slideboxWidth = $(".slidebox").width();
         let slideboxHeight = $(".slidebox").height();
         $(".slidebox .slideitem img").css({"width": slideboxWidth+"px", "height": slideboxHeight+"px" });
      } 
      </script> -->
      <div class="main_leftcontainer">

         <div class="slidebox">
            <input type="radio" name="slide" id="slide01" checked> <input
               type="radio" name="slide" id="slide02">
            <ul class="slidelist">
               <li class="slideitem"><a> <label for="slide03"
                     class="prev">&lt;</label> <img src="./img/도서문구.png"> <label
                     for="slide02" class="next">&gt;</label>
               </a></li>
               <li class="slideitem"><a> <label for="slide01"
                     class="prev">&lt;</label> <img src="./img/도서문구2.png"> <label
                     for="slide03" class="next">&gt;</label>
               </a></li>
            </ul>
         </div>
      </div>

      <!-- 컨테이너 나누기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

   </div>
   <jsp:include page="/footer.jsp" />