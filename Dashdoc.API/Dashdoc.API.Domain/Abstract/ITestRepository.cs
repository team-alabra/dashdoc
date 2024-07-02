using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract;

public interface ITestRepository
{
    Task<List<TestEntity>> GetAll();
}