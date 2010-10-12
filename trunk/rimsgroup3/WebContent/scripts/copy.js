var clip = null;

function copyToClipboard(tb) {
	var browser_type=navigator.appName;
    if(browser_type=="Microsoft Internet Explorer") {
       var div = document.getElementById(tb);
       div.focus();
       div.select();
       CopiedTxt = document.selection.createRange();
       CopiedTxt.execCommand("Copy");
    } else {
           clip = new ZeroClipboard.Client();
			clip.setHandCursor( true );
			
			clip.addEventListener('load', function (client) {
				debugstr("Flash movie loaded and ready.");
			});
			
			clip.addEventListener('mouseOver', function (client) {
				// update the text on mouse over
				clip.setText( $('fe_text').value );
			});
			
			clip.addEventListener('complete', function (client, text) {
				debugstr("Copied text to clipboard: " + text );
			});
			
			clip.glue( 'd_clip_button', 'd_clip_container' );
           
    }
}

function debugstr(msg) {
			var p = document.createElement('p');
			p.innerHTML = msg;
			$('d_debug').appendChild(p);
		}
