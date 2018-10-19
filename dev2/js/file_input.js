var input = document.getElementById("photo");
var label = document.getElementById("photo-label"), labelVal = label.innerHTML;

input.addEventListener('change', function(e) {
	var fileName = '';
	if (this.files && this.files.length > 1)
		fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
	else
		fileName = e.target.value.split( '\\' ).pop();

	if (fileName)
		label.innerHTML = fileName;
	else
		label.innerHTML = labelVal;
});