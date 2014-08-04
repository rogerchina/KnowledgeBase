// Define the namespace
var mylibrary = mylibrary || {};
mylibrary.MyComponent = function(element) {
	element.innerHTML = "<div id='main' style='height:400px'></div>" 
		    + "<div class='caption'>Hello, world!</div>"
			+ "<div class='textinput'>Enter a value: "
			+ "<input type='text' name='value'/>"
			+ "<input type='button' value='Click'/>" + "</div>";
	
	// Style it
	element.style.border = "thin solid red";
	element.style.display = "inline-block";
	// Getter and setter for the value property
	this.getValue = function() {
		return element.getElementsByTagName("input")[0].value;
	};
	this.setValue = function(value) {
		//element.getElementsByTagName("input")[0].value = value;
		require.config({
			paths:{
				'echarts' : 'http://echarts.baidu.com/build/echarts',
                'echarts/chart/bar' : 'http://echarts.baidu.com/build/echarts'
			}
		});
		
		 require(
		            [
		                'echarts',
		                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		            ],
		            function (ec) {
		                // 基于准备好的dom，初始化echarts图表
		                var myChart = ec.init(document.getElementById('main')); 
		                
		                var option = {
		                    tooltip: {
		                        show: true
		                    },
		                    legend: {
		                        data:['销量']
		                    },
		                    xAxis : [
		                        {
		                            type : 'category',
		                            data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
		                        }
		                    ],
		                    yAxis : [
		                        {
		                            type : 'value'
		                        }
		                    ],
		                    series : [
		                        {
		                            "name":"销量",
		                            "type":"bar",
		                            "data":[5, 20, 40, 10, 10, 20]
		                        }
		                    ]
		                };
		        
		                // 为echarts对象加载数据 
		                myChart.setOption(option); 
		            }
		        );
	};
	// Default implementation of the click handler
	this.click = function() {
		alert("Error: Must implement click() method");
	};
	// Set up button click
	var button = element.getElementsByTagName("input")[1];
	var self = this; // Can't use this inside the function
	button.onclick = function() {
		self.click();
	};
};


