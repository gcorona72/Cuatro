FROM ubuntu:latest
LABEL authors="rodpe"

ENTRYPOINT ["top", "-b"]