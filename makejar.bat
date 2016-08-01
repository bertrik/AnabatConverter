jar -cvfm AnabatConverter.jar AnabatConverter.manifest nl/sikken/bertrik/anabat/*.class
jarsigner -keystore bertrik.ks -storepass bertrik AnabatConverter.jar bertrik
