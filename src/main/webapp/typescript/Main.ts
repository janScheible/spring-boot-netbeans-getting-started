class Main {
    static initialize() {
		let dateTimeDiv = $('div.date-time');
		dateTimeDiv.text(dateTimeDiv.text().replace(/-/g, '.'));
    }
}

Main.initialize();