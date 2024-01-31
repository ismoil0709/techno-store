 function updateProduct(event){
    event.preventDefault();

    const formData = new FormData();

    const id = document.getElementById('id').innerText;
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const price = document.getElementById('price').value;
    const isAvailable = document.getElementById('isAvailable').value;
    const productType = document.getElementById('type').value;
    const amount = document.getElementById('amount').value;

    formData.append('id',id);
    formData.append('title',title);
    formData.append('description',description);
    formData.append('price',price);
    formData.append('isAvailable',isAvailable);
    formData.append('productType',productType);
    formData.append('amount',amount);

    console.log(formData);

    const response = fetch('/admin/update/product', {
        method: 'PATCH',
        body: formData,
    });

    if (response.ok) {
        alert("Save");
    } else {
        alert('Failed');
    }
}