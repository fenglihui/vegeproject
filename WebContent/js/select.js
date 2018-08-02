$.fn.selectDate = function(){
			var minYear = 2012;
			var maxYear = (new Date).getFullYear()-1;
			var yearSel = document.getElementById('year');
			var monthSel = document.getElementById('month');
	
			for(var y = maxYear;y >= minYear;y--){
				var yearOpt = document.createElement('option');
				yearOpt.value = y
				yearOpt.innerHTML = y+'年'
				yearSel.appendChild(yearOpt)
			}
			for(var n =2;n<=12;n++){
				var monthOpt = document.createElement('option');
                monthOpt.value = n;
				if( n<10){
					monthOpt.innerHTML = '0' + n + '月';
				}
				else{
					monthOpt.innerHTML = n + '月';
				}
				monthSel.appendChild(monthOpt);
			}
		}