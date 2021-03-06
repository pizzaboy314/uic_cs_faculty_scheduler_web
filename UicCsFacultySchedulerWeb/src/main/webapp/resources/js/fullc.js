// FUllC.js

var contextMenuEvent = null;
var changingEvent = null;

var springStart = "2015-02-09";
var betweenTime = "2015-02-20";
var fallStart = "2015-02-23";

function filterCourse(){
	var inputVal = $("#filterSearch").val().toUpperCase().trim();
	var childDivs = document.getElementById('external-events').getElementsByTagName('div');
	for (i = 0; i < childDivs.length; i++) {
		var childDiv = childDivs[i];
		if ((inputVal === "") || childDiv.innerHTML.indexOf(inputVal) > -1)
			childDiv.style.display = 'block';
		else
			childDiv.style.display = 'none';
	}
}

function swtichSemester(){
	var sem = $("#semesterTitle").text();
	if (sem === "Spring"){
		sem = "Fall";
	} else {
		sem = "Spring";
	}
	
   	if (confirm ("Are you sure you want to go to ".concat(sem).concat(" semester?"))){
   		if (sem === "Fall"){
			$('#calendar').fullCalendar('removeEvents');
   			$('#calendar').fullCalendar('gotoDate', fallStart);
   		} else {
   			$('#calendar').fullCalendar('gotoDate', springStart);
   		}
   		$("#semesterTitle").html(sem);
   	}
}

function removeEvent(){
   	if (confirm ("Are you sure you want to remove this course?")){
   		$('#calendar').fullCalendar('removeEvents', contextMenuEvent._id );
   		var isoStringS = contextMenuEvent.start.toISOString();
   		var isoStringE = contextMenuEvent.end.toISOString();
			$.post("/schedapp/CalendarRemoveServlet",
					{
						startTime: isoStringS,
						endTime: isoStringE,
						title: contextMenuEvent.title,
					}
			);
	}
};


function removeAll(){
	var sem = $("#semesterTitle").text();
	var semTime;
	if (sem === "Spring"){
		semTime = springStart;
	} else {
		semTime = fallStart;
	}
   	if (confirm ("Are you sure you want to remove all courses?")){
		$('#calendar').fullCalendar('removeEvents',
			function (event){
	       		var isoStringS = event.start.toISOString();
	       		var isoStringE = event.end.toISOString();
	       		if ((semTime == fallStart && event.start.isAfter(betweenTime)) ||
	       				(semTime == springStart && event.start.isBefore(betweenTime))){
		   			$.post("/schedapp/CalendarRemoveServlet",
		   					{
		   						startTime: isoStringS,
		   						endTime: isoStringE,
		   						title: event.title,
		   					}
		   			);
		   			return true;
	       		} else return false;
			}
		);
   	}
}

$(document).click(function(event) { 
    if(!$(event.target).closest('#contextMenu').length) {
        if($('#contextMenu').is(":visible")) {
            $('#contextMenu').hide()
        }
    }        
});

$(document).ready(function() {
	
	/* initialize the external events
	-----------------------------------------------------------------*/
	
	$('#external-events .fc-event').each(function() {
		var t = $.trim($(this).text());
		var bgc = this.style.getPropertyValue ("background-color");
		var txc = this.style.getPropertyValue ("color");
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: t, // use the element's text as the event title
			textColor: txc,
			backgroundColor: bgc,
			stick: true, // maintain when user navigates (see docs on the renderEvent method)
		});
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			helper: 'clone',
			revert: true,      // will cause the event to go back to its
			appendTo: 'body',
			revertDuration: 0,  //  original position after the drag
			scroll: false
		});
		
		$(this).tooltip()
	}
	);
	
	$('#calendar').fullCalendar({
		header: {
			left: '',
			center: '',
			right: ''
			//right: 'prev,next today'
		},
		columnFormat: { 
			month: '', 
			week: 'ddd', 
			day: '' 
		},
		defaultTimedEventDuration: document.getElementById("DEFAULT_EVENT_LENGTH").value,
	    forceEventDuration: true,
	    eventDragStop: function(event, jsEvent, ui, view){
	    	changingEvent = $.extend(true, {}, event);
	    },
	    eventDrop: function(event, delta, revertFunc, jsEvent, ui, view){
			$.post("/schedapp/CalendarUpdateServlet",
			{
				title: changingEvent.title,
				oldStartTime: changingEvent.start.toISOString(),
				oldEndTime: changingEvent.end.toISOString(),
				newStartTime: event.start.toISOString(),
				newEndTime: event.end.toISOString(),
			});
	    	
	    },
	    eventResizeStop: function(event, jsEvent, ui, view){
	    	changingEvent = $.extend(true, {}, event);
	    },
	    eventResize: function(event, delta, revertFunc, jsEvent, ui, view){
			$.post("/schedapp/CalendarUpdateServlet",
			{
				title: changingEvent.title,
				oldStartTime: changingEvent.start.toISOString(),
				oldEndTime: changingEvent.end.toISOString(),
				newStartTime: event.start.toISOString(),
				newEndTime: event.end.toISOString(),
			});
	    	
	    },
	    	
//		// Tooltip/Popout for event info
//		eventMouseover: function(calEvent, jsEvent) {
//		    var tooltip = '<div class="tooltipevent" style="width:200px;height:100px;background:#3A87AD;position:absolute;z-index:10001;color:#FFF;text-align:left;">' + calEvent.title + '</div>';
//		    $("body").append(tooltip);
//		    $(this).mouseover(function(e) {
//		        $(this).css('z-index', 10000);
//		        $('.tooltipevent').fadeIn('500');
//		        $('.tooltipevent').fadeTo('10', 1.9);
//		    }).mousemove(function(e) {
//		        $('.tooltipevent').css('top', e.pageY + 10);
//		        $('.tooltipevent').css('left', e.pageX + 20);
//		    });
//		},
//		// Hides tooltip
//		eventMouseout: function(calEvent, jsEvent) {
//		    $(this).css('z-index', 8);
//		    $('.tooltipevent').remove();
//		},
		// Ignore
	    removeEvents: function(event){
	    	removeFunc();
	    },
	    // Delete/Remove from calendar
	    eventRender: function (event, element) {
            element.find('#drop-remove').click(function() {
               $('#calendar').fullCalendar('removeEvents');
            });
            
            var $contextMenu = $("#contextMenu");
            $("body").append(contextMenu);
    		element.bind("contextmenu", "table tr", function(e) {
    			$("#cnum").html("Course: ".concat(event.title));
    			contextMenuEvent = event;
    		   $contextMenu.css({
    		      display: "inline",
    		      left: e.pageX,
    		      top: e.pageY
    		    });
    		    return false;
    		  });
    		  
    		  $contextMenu.on("click", element, function() {
    		     $contextMenu.hide();
    		  });
    		  
            element.find('#drop-remove').click(function() {
               $('#calendar').fullCalendar('removeEvents');
            });
	    },
	    //Ignore
		drop: function(date, js, ui) {
			// is the "remove after drop" checkbox checked?
			if ($('#drop-remove').is(':checked')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}
			$('#drop-remove').click( function(){
				$('#calendar').fullCalendar('removeEvents');
			});
			var eventObj = $(this).data('event');
			var copiedEventObject = $.extend({}, eventObj);
			var dateISO = date.toISOString();
			$.post("/schedapp/CalendarDropServlet",
			{
				startTime: dateISO,
				title: copiedEventObject.title,
			},
			function(data,status){
				//alert("Data: " + data + "\nStatus: " + status);
			});
		},
		// Clone "events"
		eventClick: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
		     var eventClone = {
		         title:event.title,
		         start: event.start,
		         end: event.end,
		         color: event.color
		     };
		     // Render new event with new event object
		     //$('#calendar').fullCalendar('renderEvent', eventClone);
		     // Revert the changes in parent event. To move it back to original position
		     //revertFunc();
		},
		defaultView: 'agendaWeek',
		defaultDate: '2015-02-12',
        minTime: '07:00:00',
        maxTime: '20:00:00',
        slotDuration: '00:15:00',
        weekends: false,
		allDaySlot: false,
		editable: true,
		droppable: true, // this allows things to be dropped onto the calendar
		eventLimit: true, // allow "more" link when too many events
		events : "/schedapp/CalendarRetrieveStored"
		});

	});
