var savedCardValue;

$(document).ready(function startUp(){
	generateExpMonth();
	generateExpYear();
	generateCountry();
	generateStates();
	savedCardValue=$("#cardNumber").val();
})

function generateExpMonth(){
	for(let i=1; i<13; i++){
		$("#expMonth").append($("<option>").text(i));
	}
}

function generateExpYear(){
	var year=new Date();
	year=year.getFullYear();
	for(let j=0; j<11; j++){
		$("#expYear").append($("<option>").text(year+j));
	}
}

function generateCountry(){
	
	var mostUsedCountry=["Australia", "Canada", "Francia", "Germania", "Giappone", "Grecia", "India", "Irlanda", "Italia", 
						"Nuova Zelanda", "Paesi Bassi", "Polonia", "Portogallo", "Regno Unito", "Spagna", "Stati Uniti"];
	
	var countryArr=["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua e Barbuda", "Arabia Saudita", "Argentina", 
					"Armenia", "Australia", "Austria", "Azerbaigian", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Belgio", 
					"Belize", "Benin", "Bhutan", "Bielorussia", "Bolivia", "Bosnia ed Erzegovina", "Botswana", "Brasile", "Brunei", 
					"Bulgaria", "Burkina Faso", "Burundi", "Cambogia", "Camerun", "Canada", "Capo Verde", "Ciad", "Cile", "Cina", 
					"Cipro", "Città del Vaticano", "Colombia", "Comore", "Corea del Nord", "Corea del Sud", "Costa d’Avorio", "Costa Rica", 
					"Croazia", "Cuba", "Danimarca", "Dominica", "Ecuador", "Egitto", "El Salvador", "Emirati Arabi Uniti", "Eritrea", "Estonia", 
					"Etiopia", "Figi", "Filippine", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia", "Germania", "Ghana", "Giamaica", "Giappone", 
					"Gibuti", "Giordania", "Grecia", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guinea Equatoriale", "Guyana", "Haiti", 
					"Honduras", "India", "Indonesia", "Iran", "Iraq", "Irlanda", "Islanda", "Isole Marshall", "Isole Salomone", "Israele", "Italia", 
					"Kazakistan", "Kenya", "Kirghizistan", "Kiribati", "Kuwait", "Laos", "Lesotho", "Lettonia", "Libano", "Liberia", "Libia", "Liechtenstein", 
					"Lituania", "Lussemburgo", "Macedonia", "Madagascar", "Malawi", "Maldive", "Malesia", "Mali", "Malta", "Marocco", "Mauritania", "Mauritius", 
					"Messico", "Micronesia", "Moldavia", "Monaco", "Mongolia", "Montenegro", "Mozambico", "Myanmar o Birmania", "Namibia", "Nauru", "Nepal", 
					"Nicaragua", "Niger", "Nigeria", "Norvegia", "Nuova Zelanda", "Oman", "Paesi Bassi", "Pakistan", "Palau", "Palestina", "Panama", 
					"Papua Nuova Guinea", "Paraguay", "Perù", "Polonia", "Portogallo", "Qatar", "Regno Unito", "Repubblica Ceca", "Repubblica Centrafricana", 
					"Repubblica del Congo", "Repubblica Democratica del Congo", "Repubblica Dominicana", "Romania", "Ruanda", "Russia", "Saint Kitts e Nevis", 
					"Saint Vincent e Grenadine", "Samoa", "San Marino", "Santa Lucia", "São Tomé e Príncipe", "Senegal", "Serbia", "Seychelles", "Sierra Leone", 
					"Singapore", "Siria", "Slovacchia", "Slovenia", "Somalia", "Spagna", "Sri Lanka", "Stati Uniti", "Sudafrica", "Sudan", "Sudan del Sud", 
					"Suriname", "Svezia", "Svizzera", "Swaziland", "Tagikistan", "Taiwan", "Tanzania", "Thailandia", "Timor Est", "Togo", "Tonga", 
					"Trinidad e Tobago", "Tunisia", "Turchia", "Turkmenistan", "Tuvalu", "Ucraina", "Uganda", "Ungheria", "Uruguay", "Uzbekistan", 
					"Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"];
	
	
	$("#Country").append($("<option disabled>").text("Seleziona un Paese"));
	$("#Country").append($("<option disabled>").text("------------------"));
	
	for(let i=0; i<mostUsedCountry.length; i++){
		$("#Country").append($("<option>").text(mostUsedCountry[i]));
	}
	
	$("#Country").append($("<option disabled>").text("------------------"));
	
	for(let i=0; i<countryArr.length; i++){
		$("#Country").append($("<option>").text(countryArr[i]));
	}
	
	$("#Country option:contains('Italia'):first").prop("selected", true);
}

function generateStates(){
	var italianStates=["Agrigento", "Alessandria", "Ancona", "Aosta", "Arezzo", "Ascoli Piceno", "Asti", "Avellino", "Bari", "Barletta-Andria-Trani",
					   "Belluno", "Benevento", "Bergamo", "Biella", "Bologna", "Bolzano", "Brescia", "Brindisi", "Cagliari", "Caltanissetta", "Campobasso",
					   "Carbonia-Iglesias", "Caserta", "Catania", "Catanzaro", "Chieti", "Como", "Cosenza", "Cremona", "Crotone", "Cuneo", "Enna", "Fermo",
					   "Ferrara", "Firenze", "Foggia", "Forlì-Cesena", "Frosinone", "Genova", "Gorizia", "Grosseto", "Imperia", "Isernia", "La Spezia",
					   "L'Aquila", "Latina", "Lecce", "Lecco", "Livorno", "Lodi", "Lucca", "Macerata", "Mantova", "Massa-Carrara", "Matera", "Medio Campidano",
					   "Messina", "Milano", "Modena", "Monza e Brianza", "Napoli", "Novara", "Nuoro", "Ogliastra", "Olbia-Tempio", "Oristano", "Padova",
					   "Palermo", "Parma", "Pavia", "Perugia", "Pesaro e Urbino", "Pescara", "Piacenza", "Pisa", "Pistoia", "Pordenone", "Potenza", "Prato",
					   "Ragusa", "Ravenna", "Reggio Calabria", "Reggio Emilia", "Rieti", "Rimini", "Roma", "Rovigo", "Salerno", "Sassari", "Savona", "Siena",
					   "Siracusa", "Sondrio", "Taranto", "Teramo", "Terni", "Torino", "Trapani", "Trento", "Treviso", "Trieste", "Udine", "Varese", "Venezia",
					   "Verbano-Cusio-Ossola", "Vercelli", "Verona", "Vibo Valentia", "Vicenza", "Viterbo"];
	var italianInitials=["AG", "AL", "AN", "AO", "AR", "AP", "AT", "AV", "BA", "BT", "BL", "BN", "BG", "BI", "BO", "BZ", "BS", "BR", "CA", "CL", "CB", "CI", "CE",
						 "CT", "CZ", "CH", "CO", "CS", "CR", "KR", "CN", "EN", "FM", "FE", "FI", "FG", "FC", "FR", "GE", "GO", "GR", "IM", "IS", "SP", "AQ", "LT", 
						 "LE", "LC", "LI", "LO", "LU", "MC", "MN", "MS", "MT", "VS", "ME", "MI", "MO", "MB", "NA", "NO", "NU", "OG", "OT", "OR", "PD", "PA", "PR", 
						 "PV", "PG", "PU", "PE", "PC", "PI", "PT", "PN", "PZ", "PO", "RG", "RA", "RC", "RE", "RI", "RN", "RM", "RO", "SA", "SS", "SV", "SI", "SR", 
						 "SO", "TA", "TE", "TR", "TO", "TP", "TN", "TV", "TS", "UD", "VA", "VE", "VB", "VC", "VR", "VV", "VI", "VT"];
					   
	$("#State").append($("<option disabled>").text("Seleziona una Provincia"));
	
    for(let i=0; i<italianStates.length; i++){
		$("#State").append($("<option>").text(italianStates[i]).val(italianInitials[i]));
	}
}

function toggleItaly(){
	if($("#Country").val()!="Italia"){
		$(".italyOnly").addClass("Hidden");
	} else{
		$(".italyOnly").removeClass("Hidden");
	}
}

//---------------------------------------------

function toggleBillingAddr(){
	var classes=["Hidden", "inputBAddrShow"];
	$("#inputBAddr").toggleClass(classes);
}

function toggleSecondCB(){
	var classes=["Hidden", "billingAddr"];
	
	$("#secondCheckBox").toggleClass(classes);
	
	if($("#secondCheckBox").hasClass("Hidden")==true){
		$("#addrCheckbox").prop('checked', true);
		if($("#inputBAddr").hasClass("inputBAddrShow")){
			toggleBillingAddr();
		}
	}
}

function showCard(){
	$("#inputCard").removeClass("Hidden");
	showSaveCard();
}

function showSaveCard(){
	if($("#cardNumber").val()==savedCardValue){
		$("#saveCardDiv").removeClass("billingAddr");
		$("#saveCardDiv").addClass("Hidden");
		$("#saveCardCB").prop("checked", false);
	}else{
		$("#saveCardDiv").addClass("billingAddr");
		$("#saveCardDiv").removeClass("Hidden");
	}
}

function hideCard(){
	$("#inputCard").addClass("Hidden");
}

function checkAndSubmit(event){
	event.preventDefault();
	
	if(!$("#BACheckBox").prop("checked")){
		let addr ="false";
		copyAddrToBa(addr);
		if($("#pm2").is(":checked")){
			//CashOnDelivery senza fattura
			$("#realSubmit").click();
		}else if(checkCard()){
			//Carta senza fattura
			$("#realSubmit").click();
		}
	}else {
		if($("#addrCheckbox").prop("checked")){
			let addr = $("#userSavedAddr").text();
			copyAddrToBa(addr);
			if($("#pm2").is(":checked")){
				//CashOnDelivery con fattura uguale a spedizione
				$("#realSubmit").click();
			}else if(checkCard()){
				//Carta con fattura uguale a spedizione
				$("#realSubmit").click();
			}
		}else{
			if($("#pm2").is(":checked") && checkAddr()){
				//CashOnDelivery con fattura diversa
				let addr=genBA();
				copyAddrToBa(addr);
				$("#realSubmit").click();
			}else if(checkCard() && checkAddr()){
				//Carta con fattura diversa
				let addr=genBA();
				copyAddrToBa(addr);
				$("#realSubmit").click();
			}
		}
	}
}

function checkCard(){
	var cardName=$("#cardName").val();
	var cardNumber=$("#cardNumber").val();
	var expMonth=$("#expMonth").val();
	var expYear=$("#expYear").val();
	var cardCode=$("#cardCode").val();
	
	
	var testName = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCardNumber = /^\d{16}$/
	var testCardCode = /^\d{3}$/
	var testExpMonth= new Date().getMonth()+1;
	var testExpYear = new Date().getFullYear();
	
	if(expYear>testExpYear){//anno inserito > anno corrente?
		if(cardNumber==savedCardValue){ //Carta salvata
			if(testName.test(cardName) && testCardCode.test(cardCode)){
				showErrorsCard();
				return true;
			}
		}else{ //Carta nuova
			if(testName.test(cardName) && testCardNumber.test(cardNumber) && testCardCode.test(cardCode)){
				showErrorsCard();
				return true;
			}
		}	
	}else{//anno inserito == anno corrente
		if(cardNumber==savedCardValue){ //carta salvata
			if(testName.test(cardName) && testCardCode.test(cardCode) && (testExpMonth<expMonth)){
				showErrorsCard();
				return true;
			}
		}else{ //Carta nuova
			if(testName.test(cardName) && testCardNumber.test(cardNumber) && testCardCode.test(cardCode) && (testExpMonth<expMonth)){
				showErrorsCard();
				return true;
			}
		}	
	}
	showErrorsCard();
	return false;
}

	

function checkAddr(){
	
	var nameSurname=$("#baNameSurname").val();
	var addr=$("#baStreet").val();
	var zipCode=$("#baZipCode").val();
	var city=$("#baCity").val();
	var number=$("#baNumber").val();
	
	var testNS = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCity = /^[A-Za-z\s]+$/
	var testAddr = /^[A-Za-z\s]+\d*$/
	var testZipCode = /^\d{5}$/
	var testNumber = /^\d+$/
	
	if($("#Country").val()=="Italia"){
		if(testNS.test(nameSurname) && testCity.test(city) && testAddr.test(addr) && testNumber.test(number) && testZipCode.test(zipCode)){
			//console.log("Addrtest PASS! Italy");
			showErrors();
			return true;
		} else{
			//console.log("Ecco i risultati dei test: NS: " + testNS.test(nameSurname)+", city: "+ testCity.test(city)+", addr "+ testAddr.test(addr)+ testNumber.test(number) +", zip: "+ testZipCode.test(zipCode));
		}
	}else{
		if(testNS.test(nameSurname) && testCity.test(city) && testAddr.test(addr) && testNumber.test(number)){
			//console.log("Addrtest PASS! No Ita");
			showErrors();
			return true
		} else{
			//console.log("Ecco i risultati dei test: NS: " + testNS.test(nameSurname)+", city: "+ testCity.test(city)+", addr "+ testAddr.test(addr));
		}
	}
	showErrors();
	return false;
}

function copyAddrToBa(addr){
	$.get('/NibInk/AjaxAddressServlet', {"toDo": "saveInOne", "addr": addr, "isBA": true});
}

function genBA(){
	var addr;
	
	addr=$("#baNameSurname").val()+", "+$("#baStreet").val()+" "+$("#baNumber").val()+ " " + $("#baZipCode").val();
	if(!($("#baMoreInfo").val()=="")){
		addr+=", "+$("#baMoreInfo").val();
	}
	
	addr+=", "+$("#baCity").val()+" "+$("#State").val()+", "+$("#Country").val();
	
	return addr;
}

function preventSubmitWithEnter(event) {
    if (event.keyCode === 13) {
    	event.preventDefault();
        return false;
    }
}

function showErrors(){
	var nameSurname=$("#baNameSurname").val();
	var addr=$("#baStreet").val();
	var zipCode=$("#baZipCode").val();
	var city=$("#baCity").val();
	var number=$("#baNumber").val();
	
	var testNS = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCity = /^[A-Za-z\s]+$/
	var testAddr = /^[A-Za-z\s]+\d*$/
	var testZipCode = /^\d{5}$/
	var testNumber = /^\d+$/
	
	
	
	if(!testNS.test(nameSurname)){
		$("#baNameSurname").addClass("showError");
	}else{
		$("#baNameSurname").removeClass("showError");
	}
	
	if(!testCity.test(city)){
		$("#baCity").addClass("showError");
	}else{
		$("#baCity").removeClass("showError");
	}
	
	if(!testAddr.test(addr) || addr===undefined){
		$("#baStreet").addClass("showError");
	}else{
		$("#baStreet").removeClass("showError");
	}
	
	if(!testNumber.test(number) || number===undefined){
		$("#baNumber").addClass("showError");
	}else{
		$("#baNumber").removeClass("showError");
	}
	
	if(!testZipCode.test(zipCode)){
		$("#baZipCode").addClass("showError");
	}else{
		$("#baZipCode").removeClass("showError");
	}
}

function showErrorsCard(){
	
	var cardName=$("#cardName").val();
	var cardNumber=$("#cardNumber").val();
	var expMonth=$("#expMonth").val();
	var expYear=$("#expYear").val();
	var cardCode=$("#cardCode").val();
	
	
	var testName = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCardNumber = /^\d{16}$/
	var testCardCode = /^\d{3}$/
	var testExpMonth = new Date().getMonth()+1;
	var testExpYear = new Date().getFullYear();
	
	if(!testName.test(cardName)){
		$("#cardName").addClass("showError");
	}else{
		$("#cardName").removeClass("showError");
	}
	
	
	if($("#cardNumber").val()!=savedCardValue){
		if(!testCardNumber.test(cardNumber)){
			$("#cardNumber").addClass("showError");
		}else{
			$("#cardNumber").removeClass("showError");
		}
	}else{
		$("#cardNumber").removeClass("showError");
	}
	
	if(!testCardCode.test(cardCode)){
		$("#cardCode").addClass("showError");
	}else{
		$("#cardCode").removeClass("showError");
	}
	if((testExpYear==expYear)){
		if(!(testExpMonth<expMonth)){
			$("#expMonth").removeClass("selectColor");
			$("#expMonth").addClass("showError");
			$("option").removeClass("showError");
		}else{
			$("#expMonth").removeClass("showError");
			$("#expMonth").addClass("selectColor");
		}
	}else{
			$("#expMonth").removeClass("showError");
			$("#expMonth").addClass("selectColor");
	}
}

function revertColor(){
	$("#expMonth").removeClass("showError");
	$("#expMonth").addClass("selectColor");
}
