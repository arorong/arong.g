<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>선정 도서관</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif; /* 선택한 폰트 패밀리 */
            color: #333;
            background-color: #f7f7f7; /* 선택한 배경색 */
        }

        h1, h2, h3 {
            margin: 0;
        }

        .container {
            width: 70%;
            margin: auto;
            padding: 20px;
            background-color: #fff; /* 선택한 배경색 */
            border-radius: 10px; /* 선택한 테두리 모서리 둥글게 */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 선택한 그림자 효과 */
            margin-top: 20px; /* 선택한 상단 여백 */
            overflow: hidden; /* 선택한 컨테이너 내용이 넘칠 경우 가리기 */
        }

        p {
            line-height: 1.6;
        }

        .services {
            display: flex;
            justify-content: space-around;
            margin-top: 30px;
        }

        .service {
            width: 30%;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .service h3 {
            margin-top: 0;
        }

        .about {
            padding: 20px;
            margin-top: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .ba {
            background-color: #fff;
            position: relative;
            z-index: 20;
            margin-top: 20px; /* 선택한 상단 여백 */
            border-radius: 10px; /* 선택한 테두리 모서리 둥글게 */
            padding: 20px; /* 선택한 내용 안쪽 여백 */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 선택한 그림자 효과 */
            overflow: hidden; /* 선택한 컨테이너 내용이 넘칠 경우 가리기 */
        }

        .ba p {
            margin: 0; /* 선택한 문단 위아래 여백 제거 */
        }

        .root_daum_roughmap {
            margin-top: 20px; /* 선택한 상단 여백 */
        }
        
        
        .roughmap_maker_label {
          left: 46px !important;
      }
      
      .p1 {
         display: flex;
         margin-top: 20px;
      }
      
      .divh2 {
         margin: 20px;
      }
      

    </style>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="ba">
    <div class="container">
        <h1>선정도서관 소개글</h1>
    </div>
    <div class="container">
        <h2>선정도서관의 가치와 약속</h2>
        <div class="about">
            <p>선정도서관은 방문자들이 원하는 도서를 미리 선정하고 대여할 수 있는 혁신적인 서비스를 제공하는 도서관입니다. </p>
            <p>이곳은 최신 출판물부터 인기 있는 베스트셀러, 희귀 도서에 이르기까지 다양한 장르의 책을 폭넓게 갖추고 있으며, </p>
            <p>도서 대여의 편리함과 접근성을 극대화하여 모든 연령대의 독자에게 새로운 독서 경험을 제공합니다.</p>
        </div>
    </div>
    <div class="container">
        <h2>우리의 서비스</h2>
        <div class="services">
            <div class="service">
                <h3>선정 대여 시스템</h3>
                <p>원하는 도서를 미리 선정하여 대여하세요.</p>
            </div>
            <div class="service">
                <h3>온라인 예약 플랫폼</h3>
                <p>편리한 예약 시스템을 통해 언제든지 도서를 예약하세요.</p>
            </div>
            <div class="service">
                <h3>다양한 자료 제공</h3>
                <p>전자책, 오디오북 등 다양한 형태의 자료를 제공합니다.</p>
            </div>
        </div>
    </div>
    <div class="container">
        <h2>선정도서관의 가치와 약속</h2>
        <div class="about">
            <p>선정도서관은 독서를 통한 학습과 성장, 즐거움을 최대화하고자 하는 목표를 가지고 있습니다. 우리는 모든 방문자가 원하는 책을 쉽고 빠르게 접할 수 있도록 최선을 다하며, 독서와 학습이 일상 속에서 자연스러운 부분이 될 수 있도록 지원합니다.</p>
            <p>선정도서관은 미래 지향적인 도서관 서비스를 통해 지역 사회의 지식 수준을 향상시키고, 책을 통해 사람들이 서로 연결될 수 있는 다리 역할을 하고자 합니다. 여러분이 새로운 지식을 탐구하고, 끝없는 상상의 세계로 빠져들 수 있도록 다양한 도서와 편리한 서비스를 지속적으로 제공할 것입니다.</p>
            <p>지금 바로 선정도서관을 방문하거나 온라인 플랫폼을 통해 여러분만의 독서 여정을 시작하세요. 여러분이 원하는 책, 여러분이 꿈꾸는 지식의 세계가 여기 선정도서관에 있습니다.</p>
        </div>
    </div>
    <div class="container">
       <div>
          <h2>도서관 찾아오시는 길</h2>
       </div>
       <div class="p1">
       <div id="daumRoughmapContainer1712547335268" class="root_daum_roughmap root_daum_roughmap_landing">
       </div>
          <div class="divh2">
             <h3>주소</h3>
             <p>경기도 안양시 만안구 안양로314번길 10 광창빌딩 3층</p><br>
             <h3>교통편</h3>
             <p>지하철 : 안양역 1번 출구 도보 10분거리</p>
             <p>버스 : 안양1번가 하차 09220 | 안양역 방면</p> 
             <p>마을버스 2번</p>
             <p>마을버스 9번</p><br>
             <h3>전화</h3>
             <p>031-0000-0000</p>
          </div>
       </div>
   </div>

    <script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

    <script charset="UTF-8">
        new daum.roughmap.Lander({
            "timestamp" : "1712547335268",
            "key" : "2itqn",
            "mapWidth" : "640",
            "mapHeight" : "360"
        }).render();
    </script>

    <jsp:include page="/footer.jsp"></jsp:include>
    
    <script>
    window.onload = function(){
       document.getElementsByClassName("roughmap_lebel_text")[0].innerText = "선정도서관";
//        $(".roughmap_lebel_text").text('선정도서관');
    };
    </script>
</div>
</body>
</html>