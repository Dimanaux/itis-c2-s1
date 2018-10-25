let ingredients = [];

$(document).ready(() => {
    $.ajax({
        url: '/ingredients',
        type: 'GET',
        success: (data) => {
            ingredients = data;
        }
    });
});

document.getElementById("add__button").onclick = () => {
    //Calculate amount of existing ingredients
    let form = document.getElementById("form");
    let amount = form.getElementsByClassName("ingredient").length;
    amount++;

    let newDiv = document.createElement("div");
    newDiv.classList.add("ingredient");
    form.insertBefore(newDiv, form.children[amount - 1]);

    let newInnerDiv = document.createElement("div");
    newInnerDiv.classList.add("ingredient-selection");
    newDiv.appendChild(newInnerDiv);

    let newSelect = document.createElement("select");
    newSelect.name = "ingredient-select " + amount;
    newSelect.id = "ingredient-select " + amount;
    newSelect.classList.add('ingredients-all');
    newInnerDiv.appendChild(newSelect);


    // ingredients = [{id: 1, name: "ingr1"},
    //     {id: 2, name: "ingr2"},
    //     {id: 3, name: "ingr3"},
    //     {id: 4, name: "ingr4"}];

    for (let el of ingredients) {
        let option = document.createElement("option");
        option.value = el.id;
        option.text = el.name;
        newSelect.appendChild(option);
    }
};

const sendRecipe = () => {
    let data = {};
    data.ingredients = [];
    for (let i of document.getElementsByClassName('ingredients-all')) {
        data.ingredients.push(i.value);
    }
    console.log(data);
    data.text = document.getElementById('description').value;
    data.title = document.getElementById('name').value;

    $.ajax({
        url: '/recipes',
        type: 'POST',
        data: data,
        success: (data) => {
            window.location.href = '/recipes'
        }
    });
};
