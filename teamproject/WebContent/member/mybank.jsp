<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%@ include file="../navbar.jsp" %>



<h1>${loginUser.id}님의 가계부</h1>
<body>
    <div class="container">
        <div class="row my-3">
            <div class="col-12">            
            </div>
        </div>
        <div class="row my-2">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body" width="400" height="400">
                        <canvas id="Bankdoughnut" width="400" height="400"></canvas>
                    </div>
                    <div class="card text-center text-dark">
                      <h2>지출분포</h2>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body" >
                        <canvas id="Bankbar" width="400" height="400"></canvas>
                    </div>
                  
                    <div class="card text-center text-dark">
                      <h2>가계그래프</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>



 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


    </body>
    <script type="text/javascript">
    new Chart(document.getElementById("Bankdoughnut"), {
    	type: 'bar',
    	data: {
    		labels: [
    			"식비", "주거/통신","생활용품","의복/미용","건강/문화"
    			],
    		datasets: [
    			{
    				label: "원",
    				backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
    				data: [
    						30000,10000,158979,19981,11981
    					]
    			}
    		]
    	},
    	options: {
    		scales: {
    			yAxes: [
    				{
    					ticks: {
    						beginAtZero: true
    					}
    				}
    			]
    		}
    	}
    });

    new Chart(document.getElementById("Bankbar"), {
    	type: 'line',
    	data: {
    		labels: [
    			'1', '2', '3', '4', '5', '6', '7'
    		],
    		datasets: [
    			{
    				label: 'test1',
    				fill: false,
    				data: [
    					21, 19, 25, 20, 23, 26, 25
    				],
    				backgroundColor: [
    					'rgba(255, 99, 132, 0.2)',
    					'rgba(54, 162, 235, 0.2)',
    					'rgba(255, 206, 86, 0.2)',
    					'rgba(75, 192, 192, 0.2)',
    					'rgba(153, 102, 255, 0.2)',
    					'rgba(255, 159, 64, 0.2)'
    				],
    				borderColor: [
    					'rgba(255, 99, 132, 1)',
    					'rgba(54, 162, 235, 1)',
    					'rgba(255, 206, 86, 1)',
    					'rgba(75, 192, 192, 1)',
    					'rgba(153, 102, 255, 1)',
    					'rgba(255, 159, 64, 1)'
    				],
    				borderWidth: 1
    			}
    		]
    	},
    	options: {
    		scales: {
    			yAxes: [
    				{
    					ticks: {
    						beginAtZero: true
    					}
    				}
    			]
    		}
    	}
    });


    </script>
</html>
 

 
