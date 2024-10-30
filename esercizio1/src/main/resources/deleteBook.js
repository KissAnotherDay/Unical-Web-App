// DELETE ROW ON DEMAND
document.getElementById("removeBookButton").addEventListener("click", function() {
    document.getElementById("removeBookForm").style.display = "block";
    document.getElementById("bookForm").style.display = "none";
});

// Funzione per rimuovere il libro in base al numero
function removeBookByNumber(event) {
    event.preventDefault(); // Impedisce il submit del form

    // Recupera il numero del libro da rimuovere
    const bookNumber = parseInt(document.getElementById("bookNumber").value);

    // Ottiene tutte le righe della tabella, escluse le intestazioni
    const rows =document.getElementById('bookTable').querySelectorAll('tr');

    // Controlla se il numero è valido
    if (bookNumber > 0 && bookNumber <= rows.length) {
        rows[bookNumber - 1].remove(); // Rimuove la riga corrispondente (indice base 0)
        document.getElementById("removeBookForm").style.display = "none"; // Nasconde il form
        document.getElementById("removeBookByNumberForm").reset(); // Resetta il campo del form
    } else {
        alert("Numero libro non valido!"); // Avviso per numero non valido
    }
}



// DELETE specific row
// Aggiunge la funzionalità di eliminazione per i pulsanti "Elimina" già presenti
document.querySelectorAll(".deleteButton").forEach(button => {
    button.addEventListener("click", function() {
        const row = button.closest("tr");
        row.parentNode.removeChild(row); // Elimina la riga
    });
});


// Funzione per eliminare la riga con opzioni diverse
function deleteRow(button) {
    const row = button.parentNode.parentNode; // Trova la riga corrente

    // Opzione 1: Rimuove la riga direttamente
    row.parentNode.removeChild(row);

}
