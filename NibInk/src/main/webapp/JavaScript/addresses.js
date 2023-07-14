$(document).ready(function startUp(){
	generateCountry();
	generateStates();
})

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

function checkAddr(){
	
	var nameSurname=$("#NameSurname").val();
	var addr=$("#Street").val();
	var zipCode=$("#ZipCode").val();
	var city=$("#City").val();
	var number=$("#Number").val();
	
	var testNS = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCity = /^[A-Za-z\s]+$/
	var testAddr = /^[A-Za-z\s]+\d*$/
	var testZipCode = /^\d{5}$/
	var testNumber = /^\d+$/
	
	if($("#Country").val()=="Italia"){
		if(testNS.test(nameSurname) && testCity.test(city) && testAddr.test(addr) && testNumber.test(number) && testZipCode.test(zipCode)){
			//console.log("Addrtest PASS! Italy");
			return true;
		} else{
			//console.log("Ecco i risultati dei test: NS: " + testNS.test(nameSurname)+", city: "+ testCity.test(city)+", addr "+ testAddr.test(addr)+ testNumber.test(number) +", zip: "+ testZipCode.test(zipCode));
		}
	}else{
		if(testNS.test(nameSurname) && testCity.test(city) && testAddr.test(addr) && testNumber.test(number)){
			//console.log("Addrtest PASS! No Ita");
			return true
		} else{
			//console.log("Ecco i risultati dei test: NS: " + testNS.test(nameSurname)+", city: "+ testCity.test(city)+", addr "+ testAddr.test(addr));
		}
	}
	return false;
}

function showErrors(){
	var nameSurname=$("#NameSurname").val();
	var addr=$("#Street").val();
	var zipCode=$("#ZipCode").val();
	var city=$("#City").val();
	var number=$("#Number").val();
	
	var testNS = /^([A-Za-z]+\s){1,}[A-Za-z]+$/
	var testCity = /^[A-Za-z\s]+$/
	var testAddr = /^[A-Za-z\s]+\d*$/
	var testZipCode = /^\d{5}$/
	var testNumber = /^\d+$/
	
	
	if(!testNS.test(nameSurname)){
		$("#NameSurname").addClass("showError");
	}else{
		$("#NameSurname").removeClass("showError");
	}
	
	if(!testCity.test(city)){
		$("#City").addClass("showError");
	}else{
		$("#City").removeClass("showError");
	}
	
	if(!testAddr.test(addr) || addr===undefined){
		$("#Street").addClass("showError");
	}else{
		$("#Street").removeClass("showError");
	}
	
	if(!testNumber.test(number) || number===undefined){
		$("#Number").addClass("showError");
	}else{
		$("#Number").removeClass("showError");
	}
	
	if(!testZipCode.test(zipCode)){
		$("#ZipCode").addClass("showError");
	}else{
		$("#ZipCode").removeClass("showError");
	}
}

function toggleAddressInput(){
	var regAddrs = ["Hidden", "regAddrs"];
	var inputAddrs=["Hidden", "inputAddrShow"];
	var btnClasses=["Hidden", "buttonShow"];	
	
	$("#registeredAddresses").toggleClass(regAddrs);
	$("#inputAddr").toggleClass(inputAddrs);
	$("#newAddrBtn").toggleClass(btnClasses); 
	$("#goBack").toggleClass(btnClasses);
}

function guestOnly(){
	var checkClasses=["containerCheckbox", "Hidden"];
	
	toggleAddressInput();
	if($("#addBtn").hasClass("Hidden")){
		$("#addBtn").removeClass("Hidden");
		$("#addBtn").addClass("buttonShow");
		$("#addBtn").text("Usa questo indirizzo");
	}
	
	$(".containerCheckbox").toggleClass(checkClasses);
	$("#registeredAddresses").addClass("Hidden");
}

function Reload(){
	window.location.href ="addresses.jsp";
}

//---------------------------------------------

function addNewAddress(){
	$("#NameSurname").val("");
	$("#Street").val("");
	$("#Number").val("");
	$("#ZipCode").val("");	
	$("#City").val("");
	$("#Country option:contains('Italy'):first").prop("selected", true);
	$("#State option:contains('Agrigento'):first").prop("selected", true);
	$("#MoreInfo").val("");
	
	$("#isBA").prop("checked", false);
	$("#isDefault").prop("checked", false);
	
	if($("#addBtn").hasClass("Hidden")){
		$("#addBtn").removeClass("Hidden");
		$("#addBtn").addClass("buttonShow");
	}
	
	if($("#modifyDb").hasClass("buttonShow")){
		$("#modifyDb").removeClass("buttonShow");
		$("#modifyDb").addClass("Hidden");
	}
	
	toggleAddressInput();
}


function addAddress(){
	
	var isGuest=$("#userId").val();
	
	if(checkAddr()==true){
		if(isGuest===undefined){
				saveForGuest();
			}else{
				saveForUser();
			}
	}else{
		showErrors();
	}
}


function saveForGuest(){
	
	var addr;
	
	addr=$("#NameSurname").val()+", "+$("#Street").val()+" "+$("#Number").val()+ " " + $("#ZipCode").val();
	if(!($("#MoreInfo").val()=="")){
		addr+=", "+$("#MoreInfo").val();
	}
	
	addr+=", "+$("#City").val()+" "+$("#State").val()+", "+$("#Country").val();
	//console.log(addr);
	
	
	$.get('/NibInk/AjaxAddressServlet', {"toDo": "saveInOne" ,"addrToSave": addr, "isBA": false}, 
    	function(){
			window.location.href ="payment.jsp";
		}).fail(function(){
			window.location.href ="payment.jsp";
		});
}

function saveForUser(){
	var nameSurname = $("#NameSurname").val();
	var street = $("#Street").val();
	var number = $("#Number").val();
	var zipCode = $("#ZipCode").val();	
	var city = $("#City").val();
	var state = $("#State").val();
	var country= $("#Country").val();
	var moreInfo=$("#MoreInfo").val();
	var isBA=$("#isBA").prop("checked");
	var isDe=$("#isDefault").prop("checked");
	var id=$("#userId").val();
	
	$.post('/NibInk/AjaxAddressServlet', {"toDo": "add", "id": id, "nameSurname": nameSurname, "street": street, 
										  "number": number, "moreInfo": moreInfo, "zipCode": zipCode, "city": city, 
										  "state": state, "country": country, "isBA": isBA, "isDefault": isDe}, 
    	function(){
			Reload();
		}).fail(function(){
			Reload();
		});
		
}

//---------------------------------------------

function modifyAddr(index){
	
	if($("#addBtn").hasClass("buttonShow")){
		$("#addBtn").removeClass("buttonShow");
		$("#addBtn").addClass("Hidden");
	}
	
	if($("#modifyDb").hasClass("Hidden")){
		$("#modifyDb").removeClass("Hidden");
		$("#modifyDb").addClass("buttonShow");
	}
	
	var nameSurname = $("#nameSurname"+index).text();
	var street = $("#street"+index).text();
	var number = $("#number"+index).text();
	var zipCode = $("#zipCode"+index).text();
	var city = $("#city"+index).text();
	var state = $("#state"+index).text();
	var country= $("#country"+index).text();
	var moreInfo=$("#moreInfo"+index).text();
	var isBa=$("#isBa"+index).val();
	var isDe=$("#isDe"+index).val();
	
	//console.log("ns: "+ nameSurname+" st: " + street+" number: "+ number+ " zc: "+ zipCode+" ct: "+ city + " state: "+ state + " country: "+ country+" moreinfo "+ moreInfo+ " isBA isDE "+ isBa+isDe);
	
	$("#NameSurname").val(nameSurname);
	$("#Street").val(street);
	$("#Number").val(number);
	$("#ZipCode").val(zipCode);	
	$("#City").val(city);
	$("#State").val(state);
	$("#Country option:contains('"+country+"'):first").prop("selected", true);
	$("#MoreInfo").val(moreInfo);
	
	toggleAddressInput("modify");
	
	if(isBa=="true"){
		$("#isBA").prop("checked", true);
	}else{
		$("#isBA").prop("checked", false);
	}
	if(isDe=="true"){
		$("#isDefault").prop("checked", true);
	}else{
		$("#isDefault").prop("checked", false);
	}
	
	
	$("#modifyDb").val(index);
}

function modifyAddrDb(){
	
	var nameSurname = $("#NameSurname").val();
	var street = $("#Street").val();
	var number = $("#Number").val();
	var zipCode = $("#ZipCode").val();	
	var city = $("#City").val();
	var state = $("#State").val();
	var country= $("#Country").val();
	var moreInfo=$("#MoreInfo").val();
	var isBA=$("#isBA").prop("checked");
	var isDe=$("#isDefault").prop("checked");
	var id=$("#userId").val();
	var index=$("#modifyDb").val();
	
	if(checkAddr()){
		$.post('/NibInk/AjaxAddressServlet', {"toDo": "modify", "index": index, "id": id, "nameSurname": nameSurname, "street": street, 
											  "number": number, "moreInfo": moreInfo, "zipCode": zipCode, "city": city, "state": state, 
											  "country": country, "isBA": isBA, "isDefault": isDe}, 
		  function(){
			Reload();  
		  }).fail(function(){
			Reload();
		});; 		
	}
	showErrors();
}

//---------------------------------------------

function removeAddr(index){
	var nameSurname = $("#nameSurname"+index).text();
	var street = $("#street"+index).text();
	var number = $("#number"+index).text();
	var zipCode = $("#zipCode"+index).text();
	var city = $("#city"+index).text();
	var state = $("#state"+index).text();
	var country= $("#country"+index).text();
	var moreInfo=$("#moreInfo"+index).text();
	var isBa=$("#isBa"+index).val();
	var isDe=$("#isDe"+index).val();
	var id=$("#userId").val();
	
	$("#regA"+index).addClass("Hidden");
	
	$.post('/NibInk/AjaxAddressServlet', {"toDo": "remove", "index": index, "id": id, "nameSurname": nameSurname, "street": street, 
										  "number": number, "moreInfo": moreInfo, "zipCode": zipCode, "city": city, "state": state, 
										  "country": country, "isBA": isBa, "isDefault": isDe}) 
}

function sendAddr(index){
	var nameSurname = $("#nameSurname"+index).text();
	var street = $("#street"+index).text();
	var number = $("#number"+index).text();
	var zipCode = $("#zipCode"+index).text();
	var city = $("#city"+index).text();
	var state = $("#state"+index).text();
	var country= $("#country"+index).text();
	var moreInfo=$("#moreInfo"+index).text();
	var isBa=$("#isBa"+index).val();
	
	addr=nameSurname+", "+street+" "+number+ " " + zipCode +", ";
	if(!(moreInfo=="")){
		addr+=moreInfo+", ";
	}
	
	addr+=city+" "+state+", "+country;
	
	
	$.get('/NibInk/AjaxAddressServlet', {"toDo": "saveInBoth", "addrToSave": addr, "isBA": isBa}, 
    	function(){
			window.location.href ="payment.jsp";
		});
}
