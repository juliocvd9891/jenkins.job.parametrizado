job('ejemplo2-job-DSL'){
	description('job DSL de ejemplo para el curso de Jenkins')	
    scm {
      git('https://github.com/juliocvd9891/jenkins.job.parametrizado.git', 'main') { node ->
        node / gitConfigName('juliocvd9891')
        node / gitConfigEmail('julio.vargas.condor@gmail.com')
      }
    }
  	parameters {
    	stringParam('Nombre', defaultValue = 'Julio', description = 'Parametro de cadena para el Job Booleno')
        choiceParam('Planeta', ['Mercurio', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
        booleanParam('Agent', false)
    }
    triggers {
      cron('H/20 * * * *')
    }
    steps {
      shell("sh jobscript.sh")
    }
  	       
    publishers {
      mailer('juliocvd9891@gmail.com julio.vargas.condor@gmail.com', true, true)
      slackNotifier {
        notifyAborted(true)
		notifyEveryFailure(true)
		notifyNotBuilt(false)
		notifyUnstable(false)
		notifyBackToNormal(true)
		notifySuccess(true)
		notifyRepeatedFailure(false)
		startNotification(false)
		includeTestSummary(false)
		includeCustomMessage(false)
		customMessage(null)
		sendAs(null)
		commitInfoChoice('NONE')
		teamDomain(null)
		authToken(null)
      }
    }
}
