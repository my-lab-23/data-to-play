FROM ubuntu:latest
RUN useradd ema
RUN mkdir -p /home/ema/data
COPY --chown=ema:ema ./copy /home/ema 
EXPOSE 9000
CMD ["/home/ema/run.sh"]
