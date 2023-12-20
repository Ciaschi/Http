function jsonFech(){
    fetch('path/to/boh.json')
        .then(response => response.json())
        .then(data => {
    // Access the challenges array
    const lollo = data;
    console.log(lollo);

    // Now you can work with the challenges array
  })
  .catch(error => console.error('Error fetching lollo:', error));
}