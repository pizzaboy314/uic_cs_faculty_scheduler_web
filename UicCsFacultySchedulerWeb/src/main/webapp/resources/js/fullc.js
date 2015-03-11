

$(document).ready(function() {
	
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'agendaWeek'
		},
		defaultView: 'agendaWeek',
		defaultDate: '2015-02-12',
        minTime: '07:00:00',
        maxTime: '20:00:00',
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events: [
			{
				title: 'SAMP 101',
				start: '2015-02-12T10:30:00',
				end: '2015-02-12T12:30:00'
			}
		]
	});
	
});
