
all:
	if [ -d “./Apktool” ]; then git clone git://github.com/iBotPeaches/Apktool.git; fi
	cd Apktool; ./gradlew; ./gradlew build fatJar;
	if [ -d “./aurasium” ]; then git clone https://github.com/matthewAURA/aurasium; fi
	pip install -r requirements.txt

clean:
	rm -rf Apktool/
	rm -rf aurasium/
