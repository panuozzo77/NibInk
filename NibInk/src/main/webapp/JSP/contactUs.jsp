<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invia un Messaggio</title>
</head>
<body>
    <h1>Compila il form</h1>
    <form method="post" action="/NibInk/sendMessage">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="subject">Oggetto:</label>
            <select id="subject" name="subject">
                <option value="Metodo di spedizione">Metodo di spedizione</option>
                <option value="Metodo di pagamento">Metodo di pagamento</option>
                <option value="Dov'è il mio ordine?">Dov'è il mio ordine?</option>
                <option value="Richiesta di Reso">Richiesta di Reso</option>
                <option value="Altro">Altro</option>
            </select>
        </div>
        <div>
            <label for="text">Messaggio:</label>
            <textarea id="text" name="text" required></textarea>
        </div>
        <div>
            <input type="submit" value="Invia Messaggio">
        </div>
    </form>
    <h1>form per rispondere</h1>
    <form method="post" action="/NibInk/sendMessage">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <input type="number" id="conversation" name="threadNumber" required>
        <div>
            <label for="subject">Oggetto:</label>
            <select id="subject" name="subject">
                <option value="Metodo di spedizione">Metodo di spedizione</option>
                <option value="Metodo di pagamento">Metodo di pagamento</option>
                <option value="Dov'è il mio ordine?">Dov'è il mio ordine?</option>
                <option value="Richiesta di Reso">Richiesta di Reso</option>
                <option value="Altro">Altro</option>
            </select>
        </div>
        <div>
            <label for="text">Messaggio:</label>
            <textarea id="text" name="text" required></textarea>
        </div>
        <div>
            <input type="submit" value="Invia Messaggio">
        </div>
    </form>
</body>
</html>
