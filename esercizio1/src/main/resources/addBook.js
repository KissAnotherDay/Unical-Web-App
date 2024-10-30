console.log('HELLO JS');

// Evento click sul pulsante "Aggiungi un nuovo libro"
document.getElementById("addBookButton").addEventListener("click", function() {
    // Mostra il form per aggiungere un nuovo libro impostando "display: block"
    document.getElementById("bookForm").style.display = "block";
    // nascondi il form per rimuovere il libro
    document.getElementById("removeBookForm").style.display = "none";
});

// Evento submit sul form "newBookForm"
document.getElementById("newBookForm").addEventListener("submit", function(event) {
    // Impedisce il comportamento di submit predefinito (invio dei dati)
    event.preventDefault();

    // Recupera i valori inseriti dall'utente nei campi del form
    const title = document.getElementById("title").value;
    const year = document.getElementById("year").value;
    const publisher = document.getElementById("publisher").value;

    // Riferimento alla tabella dove verranno aggiunti i nuovi libri
    const tableBody = document.getElementById("bookTable").getElementsByTagName('tbody')[0];


    addNewRowV1(tableBody , title , year , publisher);

//    addNewRowV2(tableBody , title , year , publisher);



    // Nasconde il form di aggiunta libro dopo l'inserimento
    document.getElementById("bookForm").style.display = "none";
    // Reset dei campi del form per future aggiunte
    document.getElementById("newBookForm").reset();



});


function addNewRowV1(tableBody , title, year,publisher){

    // Crea una stringa HTML per la nuova riga
    const newRowHTML = `
        <tr>
            <td>${title}</td>
            <td>${year}</td>
            <td>${publisher}</td>
            <td><button class="btn btn-danger btn-sm deleteButton"><i class="fa-solid fa-trash"></i></button></td>
        </tr>
    `;

    // Aggiunge la nuova riga alla tabella usando innerHTML
    tableBody.innerHTML += newRowHTML;

    console.log('Adding new Row , with method 1');
}


function addNewRowV2(tableBody , title, year,publisher){

    // Crea una nuova riga nella tabella
    const newRow = tableBody.insertRow();

    // Creazione delle celle per la nuova riga e inserimento dei dati
    const titleCell = newRow.insertCell(0);    // Cella per il titolo
    const yearCell = newRow.insertCell(1);     // Cella per l'anno
    const publisherCell = newRow.insertCell(2); // Cella per la casa editrice
    const deleteButtonCell = newRow.insertCell(3); // Cella per il bottone elimina

    // Assegnazione dei valori alle celle della nuova riga
    titleCell.textContent = title;  // Inserisce il titolo nella prima cella
    yearCell.innerHTML = `<span class="badge bg-primary">${year}</span>`; // Inserisce l'anno con badge
    publisherCell.textContent = publisher; // Inserisce la casa editrice nella terza cella
    deleteButtonCell.innerHTML =`<button class="btn btn-danger btn-sm deleteButton"><i class="fa-solid fa-trash"></i></button>`


    console.log('Adding new Row , with method 2');
}
