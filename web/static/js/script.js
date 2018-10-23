const logout = () => {
    $.ajax({
        url: '/logout',
        type: 'POST'
    });
};
