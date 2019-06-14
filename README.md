# AvailityHW!

The following content explains basic execution, tests, and reservations about my implememtation of these projects.


### LISPChecker

The LISPChecker takes in either a direct string input or a  .txt input file to begin execution:

.txt input
```sh
$ java Main {filepath)\{filename}.txt
```

direct input
```sh
 java Main (defun csg-intersection-intersect-all (obj-a obj-b)   (lambda (ray)
```

| Test Cases | 
| ------ | 
| AvailityAssessment\LISPChecker\tests\\ | 

### EnrollmentParser

The Enrollment Parser takes in an inputFile for arg[0] and you can specify an output path for arg[1]:

```sh
$ java Main {filepath)\{inputFilename}.txt {filepathOut}\
```
| Test Cases | 
| ------ | 
| AvailityAssessment\EnrollmentParser\tests\\ |  

##### Reservations

- Did not complete duplicateCheck
- csv columns are currenly expected to be in the format [UserID, First and Last, Version, Insurancy Company]
- could have included csv Headers to keep better track of columns