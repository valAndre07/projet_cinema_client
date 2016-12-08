function convertDate(id) {

		$('[id^='+id+']').each(function() {
			var oldDate = $(this).text();
			var values = oldDate.split(' ');
	    	var day = values[2];
	    	var year = values[5];
	    	var month = "";
			switch (values[1])
			{
				case "Jan":
					month = "01";
					break;
				case "Feb":
					month = "02";
					break;
				case "Mar":
					month = "03";
					break;
				case "Apr":
					month = "04";
					break;
				case "May":
					month = "05";
					break;
				case "Jun":
					month = "06";
					break;
				case "Jul":
					month = "07";
					break;
				case "Aug":
					month = "08";
					break;
				case "Sep":
					month = "09";
					break;
				case "Oct":
					month = "10";
					break;
				case "Nov":
					month = "11";
					break;
				case "Dec":
					month = "12";
					break;
			}
	    	var newDate = day+"/"+month+"/"+year;
	    	if (newDate=="undefined//undefined")
	    	{
	    		$(this).text("");
	    	}
	    	else
	    	{
	    		$(this).text(newDate);
	    	}	
		});
    	
    }

function convertDateInput(id) {

		var oldDate = $(id).val();
		console.log(oldDate);
		var values = oldDate.split(' ');
    	var day = values[2];
    	var year = values[5];
    	var month = "";
		switch (values[1])
		{
			case "Jan":
				month = "01";
				break;
			case "Feb":
				month = "02";
				break;
			case "Mar":
				month = "03";
				break;
			case "Apr":
				month = "04";
				break;
			case "May":
				month = "05";
				break;
			case "Jun":
				month = "06";
				break;
			case "Jul":
				month = "07";
				break;
			case "Aug":
				month = "08";
				break;
			case "Sep":
				month = "09";
				break;
			case "Oct":
				month = "10";
				break;
			case "Nov":
				month = "11";
				break;
			case "Dec":
				month = "12";
				break;
		}
    	var newDate = month+"/"+day+"/"+year;
    	
    	if (newDate=="/undefined/undefined")
    	{
    		$(id).val("");
    	}
    	else
    	{
    		$(id).val(newDate);
    	}
	}