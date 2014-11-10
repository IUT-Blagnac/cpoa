if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  echo -e "Starting to update gh-pages on GitHub (http://jmbruel.github.io/idm2014/)\n"

  #copy data we're interested in to other place
  cp -R output/*.html $HOME

  #go to home and setup git
  cd $HOME
  git config --global user.email "jbruel#travis@gmail.com"
  git config --global user.name "Jean-Michel Bruel"

  #using token clone gh-pages branch
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/jmbruel/idm2014.git  gh-pages > /dev/null

  #go into directory and copy data we're interested in to that directory
  cd gh-pages
  cp -Rf $HOME/*.html .
  cp ./main.html ./index.html

  #add, commit and push files
  git add -f .
  git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed to gh-pages -- [skip ci]"
  git push -fq origin gh-pages > /dev/null

  echo -e "Done magic\n"
fi
