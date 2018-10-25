let ul = document.getElementById('sidemenu');

ul.addEventListener('click', function(e) {
    if (e.target.tagName === 'LI'){
      window.location.replace(e.target.children[0].href);
    }
});
