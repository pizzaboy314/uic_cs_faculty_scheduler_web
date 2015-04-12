
$(document).ready(function() {
	
	/* initialize the external events
	-----------------------------------------------------------------*/
	

	$('#external-events .fc-event').each(function() {

		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: $.trim($(this).text()), // use the element's text as the event title
			stick: true // maintain when user navigates (see docs on the renderEvent method)
		});
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
		$(this).popover();
		
	
	}
	);
	
	

		$('#calendar').fullCalendar(
				{
					// {
					header : {
						left : 'prev,next today',
						center : 'title',
						right : 'agendaWeek'
					},
					/*
					* eventClick: function(calEvent, jsEvent, view) {
					* 
					* alert('Event: ' + calEvent.title); $(this).js('popover'); // change
					* the border color just for fun //$(this).css('border-color', 'red');
					* //$('.fc-event').popover('show'); },
					*/
					
					drop : function() {
									// is the "remove after drop" checkbox checked?
									if ($('#drop-remove').is(':checked')) {
										// if so, remove the element from the "Draggable Events" list
										$(this).remove();
									}
									if ($('.fc-event').dblclick(function() {
										// $(this).remove();
										alert("Removing  a course");
										if (!confirm("Are you sure?")) {
											$('#calendar').fullCalendar('refetchEvents');
										} else {
											$('#calendar').fullCalendar('removeEvents');
										}
									})) {
					}
					
					},
					defaultView : 'agendaWeek',
					defaultDate : '2015-02-12',
					minTime : '07:00:00',
					maxTime : '20:00:00',
					weekends : false,
					allDaySlot : false,
					editable : true,
					droppable : true, // this allows things to be dropped onto the
					// calendar
					eventLimit : true, // allow "more" link when too many events
					events : "/CalendarJsonServlet"
				}
// {
// header : {
// left : 'prev,next today',
//				center : 'title',
//				right : 'agendaWeek'
//			},
//			/*
//			 * eventClick: function(calEvent, jsEvent, view) {
//			 * 
//			 * alert('Event: ' + calEvent.title); $(this).js('popover'); // change
//			 * the border color just for fun //$(this).css('border-color', 'red');
//			 * //$('.fc-event').popover('show'); },
//			 */
//	
//			drop : function() {
//				// is the "remove after drop" checkbox checked?
//				if ($('#drop-remove').is(':checked')) {
//					// if so, remove the element from the "Draggable Events" list
//					$(this).remove();
//				}
//				if ($('.fc-event').dblclick(function() {
//					// $(this).remove();
//					alert("Removing  a course");
//					if (!confirm("Are you sure?")) {
//						$('#calendar').fullCalendar('refetchEvents');
//					} else {
//						$('#calendar').fullCalendar('removeEvents');
//					}
//				})) {
//				}
//	
//			},
//			defaultView : 'agendaWeek',
//			defaultDate : '2015-02-12',
//			minTime : '07:00:00',
//			maxTime : '20:00:00',
//			weekends : false,
//			allDaySlot : false,
//			editable : true,
//			droppable : true, // this allows things to be dropped onto the
//			// calendar
//			eventLimit : true, // allow "more" link when too many events
//			events : [
//	
//			{
//				title : 'SAMP 101',
//				start : '2015-02-12T10:30:00',
//				end : '2015-02-12T12:30:00'
//			}
//	
//			]
//	
//		}
	);
	

	
	
			
	
});
