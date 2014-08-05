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
		                	    title : {
		                	        text: '未来一周气温变化',
		                	        subtext: '纯属虚构'
		                	    },
		                	    tooltip : {
		                	        trigger: 'axis'
		                	    },
		                	    legend: {
		                	        data:['最高气温','最低气温']
		                	    },
		                	    toolbox: {
		                	        show : true,
		                	        feature : {
		                	            mark : {show: true},
		                	            dataView : {show: true, readOnly: false},
		                	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		                	            restore : {show: true},
		                	            saveAsImage : {show: true}
		                	        }
		                	    },
		                	    calculable : true,
		                	    xAxis : [
		                	        {
		                	            type : 'category',
		                	            boundaryGap : false,
		                	            data : ['周一','周二','周三','周四','周五','周六','周日']
		                	        }
		                	    ],
		                	    yAxis : [
		                	        {
		                	            type : 'value',
		                	            axisLabel : {
		                	                formatter: '{value} °C'
		                	            }
		                	        }
		                	    ],
		                	    series : [
		                	        {
		                	            name:'最高气温',
		                	            type:'line',
		                	            data:[11, 11, 15, 13, 12, 13, 10],
		                	            markPoint : {
		                	                data : [
		                	                    {type : 'max', name: '最大值'},
		                	                    {type : 'min', name: '最小值'}
		                	                ]
		                	            },
		                	            markLine : {
		                	                data : [
		                	                    {type : 'average', name: '平均值'}
		                	                ]
		                	            }
		                	        },
		                	        {
		                	            name:'最低气温',
		                	            type:'line',
		                	            data:[1, -2, 2, 5, 3, 2, 0],
		                	            markPoint : {
		                	                data : [
		                	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
		                	                ]
		                	            },
		                	            markLine : {
		                	                data : [
		                	                    {type : 'average', name : '平均值'}
		                	                ]
		                	            }
		                	        }
		                	    ]
		                	};
		                	                    
		                var option1 = {
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


