/**
 * 
 */
new Chart(document.getElementById("Bankdoughnut"), {
	type: 'doughnut',
	data: {
		labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
		datasets: [
			{
				label: "Population (millions)",
				backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
				data: [2478, 5267, 734, 784, 433]
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


