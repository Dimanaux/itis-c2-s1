const logout = () => {
    $.ajax({
        url: '/logout',
        type: 'POST',
        success: (data) => {
            window.location.href = '/recipes'
        }
    });
};
