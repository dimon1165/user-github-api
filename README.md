# User vcs data retriever

### Description

Simple service for retrieving users repositories with branches and last commits sha1.

### Technologies used:

* `Java 11`
* `Gradle`
* `Spring Cloud 2021.0.5`

### Important:

If you would like to work with auth protected GitHub api endpoints you need
to add security configuration to the service and create personal token or github application.

Api limited by GitHub API rate limits:

* https://docs.github.com/en/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#rate-limiting

### Run service on local machine:

1. Run from project root:

```
  gradle clean build
```

2. And then boot run gradle via command:

```
  gradle bR
```

### Build docker image manually and run in container locally:

1. Run from project root

```
  docker build -t user-github-api .
```

2. Check if image created - there should be image with name `user-github-api`:

```
  docker images
```

3. Run image on local docker machine:

```
  docker run -p 8080:8080  user-github-api
```

### Run app on AWS with CloudFormation(requires some manual actions):

* Precondition is to install and configure aws cli
* Cloud Formation scripts are in infrastructure folder of project

1. Create Amazon ECR Repository via cli or Aws console:
    ```
    aws ecr create-repository --repository-name user-github-api
    ```
   Response will look like:
    ```
    {
        {
        "repository": {
            "repositoryArn": "arn:aws:ecr:us-west-2:863638139155:repository/user-github-api",
            "registryId": "863638139155",
            "repositoryName": "user-github-api",
            "repositoryUri": "863638139155.dkr.ecr.us-west-2.amazonaws.com/user-github-api",
            "createdAt": "2022-12-18T12:34:37+02:00",
            "imageTagMutability": "MUTABLE",
            "imageScanningConfiguration": {
                "scanOnPush": false
            },
            "encryptionConfiguration": {
                "encryptionType": "AES256"
            }
        }
    }
    ```
2. Login into ECR:
   ```
    aws ecr get-login-password | sh
   ```
3. Re-tag docker image:
    ```
    docker tag user-github-api 863638139155.dkr.ecr.us-west-2.amazonaws.com/user-github-api:v1
    ```
4. Push to image to ECR:
    ```
    docker push 863638139155.dkr.ecr.us-west-2.amazonaws.com/user-github-api:v1
    ```
5. In order to create stacks on aws use command:
   ```
   aws cloudformation create-stack --stack-name <choose_name_of_stack> \
   --template-body file://$PWD/infrastracture/<name_of_stack in infrastracture folder>.yaml
   ```
6. IAM stack requires capabilities creation. Use command:
   ```
   aws cloudformation create-stack --stack-name iam \
   --template-body file://$PWD/infrastracture/iam_roles .yaml --capabilities CAPABILITY_IAM
   ```

### API documentation

Api documentation available by reference upon running application:

* http://localhost:8080/swagger-ui/index.html