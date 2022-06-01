# Local-Brand web app

# Git Rule:
# 1: clone project:
  code:  git config --global user.name "Your Name" 
         git config --global user.email YourEmail@example.com
         git clone https://github.com/haihpse150218/Local-Brand.git
  

# 2: Rules before we play with new feature:
    step1: make sure everything are ready:
      step 1-1: comback dev branch and make sure you pull everything
        code: git checkout dev
              git fetch
              git pull
    step2: create new branch from dev branch:
      code: git checkout -b [Name of new feature] dev 
            ex: "git checkout -b login dev" ==> create login branch frome dev branch
      code: git add .
            git commit -m "Write document here"
    step3: push to remote
      code: git push -u origin [Name of new feature]
      
    step 4: pull request and wating to merch
        
        
    
   
  
  
  
