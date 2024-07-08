Questo file contiene lo pseudocodice per testare tre funzionalità: 
1. Test di Login
2. Test di visualizzazione prodotti senza Login (inclusa l'aggiunta e l'eliminazione di prodotti)
3. Test di un acquisto completo dall'inizio alla fine

## Pseudocodice

### Test di Login

1. Avviare l'applicazione
- cliccalre sui tre puntini
   by.xpaht(//android.view.ViewGroup[@content-desc="open menu"]/android.widget.ImageView)
 - clicclare su logIn
 	AppiumBy.accessibilitiId(menu item log in);
2. Individuare il campo di input per l'username
    - AppiumBy.accessibilitiId("Username input field")
3. Inserire l'username(bob@example.com)
4. Individuare il campo di input per la password
    - AppiumBy.accessibilitiId("Password input field")
5. Inserire la password(10203040)
6. Individuare il pulsante di login
    - AppiumBy.accessibilitiId("Login button")
7. Cliccare sul pulsante di login
8. Verificare il login controllando assenza di un elemento della schermata 
    - AppiumBy.accessibilitiId("Login button")

### Test prodotti (senza Login) con aggiunta e eliminazione di prodotti

Inizio del test prodottiTest:
    Definisci il locator "carello" con attributo di accessibilità "Add To Cart button".
    Clicca sul secondo prodotto nella lista utilizzando XPath.
    Fai clic sull'icona del menu utilizzando XPath.
    Fai clic sul pulsante "Catalog" utilizzando XPath.
    Clicca sul primo prodotto nella lista utilizzando XPath.
    Definisci il locator "iconaCarrello" per l'icona del carrello utilizzando XPath.
    Fai clic sull'icona del carrello utilizzando XPath.
    Fai clic sul pulsante "Rimuovi Prodotto" utilizzando XPath.
    Fai clic sul pulsante "Aggiungi" utilizzando l'attributo di accessibilità "counter plus button".
    Verifica se il secondo elemento "Rimuovi Prodotto" è presente nel carrello utilizzando XPath.
    Verifica che il prodotto sia stato eliminato correttamente dal carrello.

Funzione aggiungiAlCarrello(con locatorProdotto, locatorPulsanteAggiungi):
    Clicca sul prodotto utilizzando il locatorProdotto.
    Clicca sul pulsante "Aggiungi" utilizzando il locatorPulsanteAggiungi.
    Verifica che l'elemento "iconaCarrello" sia visibile utilizzando XPath.

### Acquisto Totale dall'inizio alla fine

Funzione acquistoCompleto():
    Clicca sull'icona del menu utilizzando XPath
    Clicca sul pulsante "Log In" utilizzando l'attributo di accessibilità "menu item log in"
    Inserisci l'username utilizzando l'attributo di accessibilità "Username input field"
    Inserisci la password utilizzando l'attributo di accessibilità "Password input field"
    Clicca sul pulsante "Login" utilizzando l'attributo di accessibilità "Login button"
    Verifica se il login è avvenuto con successo
    Aggiungi prodotti al carrello e procedi al checkout

Funzione aggiungiProdottiECassa():
    Aggiungi prodotti al carrello utilizzando XPath
    Vai alla cassa utilizzando l'attributo di accessibilità "Proceed To Checkout button"
    Inserisci i dati per la spedizione
    Procedi al pagamento utilizzando l'attributo di accessibilità "To Payment button"

Funzione inserisciDatiSpedizione():
    Inserisci i dati per la spedizione utilizzando gli attributi di accessibilità
    Verifica che tutti i campi siano stati compilati correttamente

Inizio del test acquistoCompleto:
    acquistoCompleto()