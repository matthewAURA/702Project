
deps:
		git clone https://github.com/matthewAURA/aurasium
		pip install -r requirements.txt
		git clone git://github.com/iBotPeaches/Apktool.git
		cd Apktool; ./gradlew; ./gradlew build fatJar

all: deps


clean:
	rm -rf Apktool/
	rm -rf aurasium/
