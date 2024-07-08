										ReadMeBookApp
								
//Test case 1: Login test KO
Apri la pagina "https://demoqa.com/login"
Inserisci "usernameErrato" nel campo "UserName"
Inserisci "passwordErrata" nel campo "Password"
Clicca su bottone "Login"
Verifica che l'url della pagina sia rimasto lo stesso ("https://demoqa.com/login")
Verifica che nella pagina ci sia il messaggio di testo "Invalid username or password!"
Verifica che il bottone "Login" sia ancora visibile nella pagina
 
//Test case 2: Login test OK
Apri la pagina "https://demoqa.com/login"
Inserisci "usernameCreato" nel campo "UserName"
Inserisci "passwordCreata" nel campo "Password"
Clicca su bottone "Login"
Verifica che l'url della pagina sia diverso da "https://demoqa.com/login" ("https://demoqa.com/profile")
Verifica che nella pagina ci sia "User Name :usernameCreato"
Verifica che il bottone "Login" non sia più visibile nella nuova pagina
Verifica che il bottone "Log out" sia visibile nella nuova pagina
    
//Test case 3: Register test KO
Apri la pagina "https://demoqa.com/books"
Clicca sul bottone del menu laterale "Profile"
Verifica che l'url della pagina aperta sia https://demoqa.com/profile
All'interno della pagina cliccare sul link "register"
Verifica che l'url della pagina aperta sia "https://demoqa.com/register"
Inserire nel campo First Name il nome da associare all'account
Inserire nel campo Last Name il cognome da associare all'account
Inserire nel campo UserName lo username da utilizzare per l'account
Inserire nel campo Password la password "123"
Cliccare sul check del captcha (nel caso non sia automatizzabile
	, visualizzare una message dialog - JOptionPane.showMessageDialog 
	e quando risolto manualmente 
	cliccare su quest'ultima per procedere con l'esecuzione del test)
Clicca su bottone "Register"
Verifica che l'url della pagina sia rimasto lo stesso ("https://demoqa.com/register")
Verifica che nella pagina ci sia il messaggio di testo "Passwords must have at least one non alphanumeric character, one digit ('0'-'9')
, one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."
Verifica che il bottone "Register" sia ancora visibile nella pagina
 
//Test case 4: Register test OK - Login - BookStore
Apri la pagina "https://demoqa.com/books"
Clicca sul bottone del menu laterale "Profile"
Verifica che l'url della pagina aperta sia https://demoqa.com/profile
All'interno della pagina cliccare sul link "register"
Verifica che l'url della pagina aperta sia "https://demoqa.com/register"
Inserire nel campo First Name il nome da associare all'account
Inserire nel campo Last Name il cognome da associare all'account
Inserire nel campo UserName lo username da utilizzare per l'account
Inserire nel campo Password la password corretta per procedere con la registrazione
Cliccare sul check del captcha (nel caso non sia automatizzabile
	, visualizzare una message dialog - JOptionPane.showMessageDialog 
	e quando risolto manualmente 
	cliccare su quest'ultima per procedere con l'esecuzione del test)
Clicca su bottone "Register"
Verifica che sia stato visualizzato il popup contenente il testo "User Register Successfully"
Cliccare su "OK" del popup
Apri la pagina "https://demoqa.com/login"
Inserisci lo username creato prima nel campo "UserName"
Inserisci la password creata prima nel campo "Password"
Clicca su bottone "Login"
Verifica che nella pagina ci sia "User Name :usernameCreato"
Verifica che il bottone "Log out" sia visibile nella nuova pagina
Cliccare su "Go To Book Store"
Verifica che l'url della pagina aperta sia "https://demoqa.com/books"
Cliccare sul libro "You Don't Know JS" 
Verifica che l'url della pagina aperta sia "https://demoqa.com/books?book=9781491904244"
Aprire la pagina "https://demoqa.com/books"
Cliccare sul bottone "Log out"
Verifica che l'url della pagina aperta sia "https://demoqa.com/login" 